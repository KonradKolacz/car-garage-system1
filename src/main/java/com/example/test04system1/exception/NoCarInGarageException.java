package com.example.test04system1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoCarInGarageException extends RuntimeException{

    private long garageId;
    private long carId;

    public String getMessage(){
        return "Car id " + carId + " is not parked in garage id " + garageId;
    }


}
