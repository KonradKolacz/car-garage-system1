package com.example.test04system1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForbiddenTypeLPGException extends RuntimeException {

    private long garageId;
    private long carId;

    public String getMessage() {
        return "Garage id " + garageId + ": LPG vehicles(e.g. car id " + carId + " are not allowed to enter";
    }

}
