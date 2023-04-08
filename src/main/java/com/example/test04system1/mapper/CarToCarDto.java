package com.example.test04system1.mapper;

import com.example.test04system1.domain.Car;
import com.example.test04system1.dto.CarDto;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class CarToCarDto implements Converter<Car, CarDto> {

    @Value("${car.alerts.url}")
    private String urlAlertsByCarId;


    @Override
    public CarDto convert(MappingContext<Car, CarDto> mappingContext) {
        Car car = mappingContext.getSource();
        CarDto carDto = CarDto.builder()
                .id(car.getId())
                .mark(car.getMark())
                .model(car.getModel())
                .driveType(car.getDriveType())
                .yearOfProduction(car.getYearOfProduction())
                .build();
        carDto.add(Link.of(urlAlertsByCarId + carDto.getId(), "alerts"));
        return carDto;
    }
}
