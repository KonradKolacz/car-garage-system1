package com.example.test04system1.service;

import com.example.test04system1.domain.Car;
import com.example.test04system1.exception.ObjectNotFoundException;
import com.example.test04system1.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Car.class.getName()));
    }

}
