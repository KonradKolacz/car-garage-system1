package com.example.test04system1.controller;

import com.example.test04system1.domain.Alert;
import com.example.test04system1.dto.ExceptionDto;
import com.example.test04system1.exception.*;
import com.example.test04system1.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final AlertService alertService;

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleObjectNotFoundException(ObjectNotFoundException e) {
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenTypeLPGException.class)
    public ResponseEntity<ExceptionDto> handleForbiddenTypeLPGException(ForbiddenTypeLPGException e, HttpServletRequest request) {
        alertService.addAlert(new Alert(request.getRemoteAddr(), LocalDate.now(), e.getCarId(), e.getGarageId()));
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCarInGarageException.class)
    public ResponseEntity<ExceptionDto> handleNoCarInGarageException(NoCarInGarageException e, HttpServletRequest request) {
        alertService.addAlert(new Alert(request.getRemoteAddr(), LocalDate.now(), e.getCarId(), e.getGarageId()));
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFreeParkingSpacesException.class)
    public ResponseEntity<ExceptionDto> handleNoFreeParkingSpacesException(NoFreeParkingSpacesException e, HttpServletRequest request) {
        alertService.addAlert(new Alert(request.getRemoteAddr(), LocalDate.now(), e.getCarId(), e.getGarageId()));
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CarAssignedToGarageException.class)
    public ResponseEntity<ExceptionDto> handleCarAssignedToGarageException(CarAssignedToGarageException e, HttpServletRequest request) {
        alertService.addAlert(new Alert(request.getRemoteAddr(), LocalDate.now(), e.getCarId(), e.getGarageId()));
        return new ResponseEntity<>(new ExceptionDto(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
