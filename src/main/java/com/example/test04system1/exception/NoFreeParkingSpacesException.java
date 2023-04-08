package com.example.test04system1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoFreeParkingSpacesException extends RuntimeException{

    private long garageId;
    private long carId;

    public String getMessage(){
        return "Garage id " + garageId + ": is full. There are no parking spaces available for car id " + carId;
    }

}
