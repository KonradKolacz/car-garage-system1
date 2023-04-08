package com.example.test04system1.service;

import com.example.test04system1.domain.Car;
import com.example.test04system1.domain.Garage;
import com.example.test04system1.exception.CarAssignedToGarageException;
import com.example.test04system1.exception.ForbiddenTypeLPGException;
import com.example.test04system1.exception.NoCarInGarageException;
import com.example.test04system1.exception.NoFreeParkingSpacesException;
import com.example.test04system1.model.DriveType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidateService {


    public void validateAvailableSpaceInGarage(Garage garage, Car car){
        if (garage.getNumberOfFreeParkingPlaces() == 0){
            throw new NoFreeParkingSpacesException(garage.getId(), car.getId());
        }
    }

    public void validateDriveType(Garage garage, Car car){
        if (car.getDriveType().equals(DriveType.LPG) && !garage.isAllowedLpg()){
            throw new ForbiddenTypeLPGException(garage.getId(), car.getId());
        }
    }

    public void validateCarIsNotParked(Garage garage, Car car){
        if (car.getGarage()!=null){
            throw new CarAssignedToGarageException(garage.getId(), car.getId());
        }
    }

    public void validateCarInThatGarage(Garage garage, Car car){
        if (garage.getCars().stream().map(Car::getId).noneMatch(id -> Objects.equals(id, car.getId()))){
            throw new NoCarInGarageException(garage.getId(), car.getId());
        }
    }
}
