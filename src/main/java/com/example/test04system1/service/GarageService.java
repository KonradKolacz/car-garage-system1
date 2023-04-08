package com.example.test04system1.service;

import com.example.test04system1.domain.Car;
import com.example.test04system1.domain.Garage;
import com.example.test04system1.exception.*;
import com.example.test04system1.repository.CarRepository;
import com.example.test04system1.repository.GarageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GarageService {

    private final GarageRepository garageRepository;
    private final CarRepository carRepository;
    private final ValidateService validateService;

    @Transactional
    public Garage addCar(Long garageId, Long carId) {
        Garage garageById = garageRepository.findWithLockingById(garageId).orElseThrow(() -> new ObjectNotFoundException(garageId, Garage.class.getName()));
        Car carById = carRepository.findWithLockingById(carId).orElseThrow(() -> new ObjectNotFoundException(carId, Car.class.getName()));

        validateService.validateAvailableSpaceInGarage(garageById, carById);
        validateService.validateDriveType(garageById, carById);
        validateService.validateCarIsNotParked(garageById, carById);

        garageById.addCar(carById);
        return garageById;
    }

    @Transactional
    public Garage removeCar(Long garageId, Long carId) {
        Garage garageById = garageRepository.findWithLockingById(garageId).orElseThrow(() -> new ObjectNotFoundException(garageId, Garage.class.getName()));
        Car carById = carRepository.findWithLockingById(carId).orElseThrow(() -> new ObjectNotFoundException(carId, Car.class.getName()));

        validateService.validateCarInThatGarage(garageById, carById);

        garageById.removeCar(carById);
        return garageById;
    }
}
