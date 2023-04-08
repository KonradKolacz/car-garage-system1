package com.example.test04system1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarAssignedToGarageException extends RuntimeException{

    private long garageId;
    private long carId;

    public String getMessage(){
        return "Car id " + carId + " cant be parked in garage id " + garageId + ". First remove car from other garage.";
    }


}
