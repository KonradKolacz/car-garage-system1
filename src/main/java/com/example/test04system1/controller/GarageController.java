package com.example.test04system1.controller;

import com.example.test04system1.dto.GarageDto;
import com.example.test04system1.service.GarageService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/garages")
public class GarageController {

    private final GarageService garageService;
    private final ModelMapper modelMapper;

    @PutMapping("/{id}/cars/{carId}/add")
    public ResponseEntity<GarageDto> addCarToGarage(@PathVariable("id") Long id, @PathVariable("carId") Long carId) {
        return new ResponseEntity<>(modelMapper.map(garageService.addCar(id, carId), GarageDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}/cars/{carId}/remove")
    public ResponseEntity<GarageDto> removeCarFromGarage(@PathVariable("id") Long id, @PathVariable("carId") Long carId) {
        return new ResponseEntity<>(modelMapper.map(garageService.removeCar(id, carId), GarageDto.class), HttpStatus.OK);
    }
}
