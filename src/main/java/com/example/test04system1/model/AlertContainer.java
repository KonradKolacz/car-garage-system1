package com.example.test04system1.model;

import com.example.test04system1.domain.Alert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class AlertContainer {

    private final Map<Alert, Integer> map = new ConcurrentHashMap<>();

    public void addAlert(Alert alert) {
        map.compute(alert, (key, value) -> value == null ? 1 : value + 1);
    }

    public void removeAlert(Alert alert) {
        map.remove(alert);
    }

    public int getNumberOfAlertRepetitions(Alert alert) {
        return map.get(alert);
    }

}
