package com.example.test04system1.service;

import com.example.test04system1.domain.Alert;
import com.example.test04system1.model.AlertContainer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
@RequiredArgsConstructor
public class AlertService {


    private final AlertContainer alertContainer;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final String queueName = "alert";
    @Value("${alertService.maxAlert}")
    private int maxAlert;
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Lock writeLock = lock.writeLock();

    public void addAlert(Alert alert) {
        writeLock.lock();

        alertContainer.addAlert(alert);
        if (alertContainer.getNumberOfAlertRepetitions(alert) == maxAlert) {
            sendAlert(alert);
            alertContainer.removeAlert(alert);
        }

        writeLock.unlock();
    }

    public void sendAlert(Alert alert) {
        String alertJson;
        try {
            alertJson = objectMapper.writeValueAsString(alert);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        rabbitTemplate.convertAndSend(queueName, alertJson);
    }


}
