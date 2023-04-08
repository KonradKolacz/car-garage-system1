package com.example.test04system1.mapper;

import com.example.test04system1.domain.Car;
import com.example.test04system1.domain.Garage;
import com.example.test04system1.dto.CarDto;
import com.example.test04system1.dto.GarageDto;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class GarageToGarageDto implements Converter<Garage, GarageDto> {

    @Value("${garage.alerts.url}")
    private String urlAlertsByGarageId;

    @Value("${car.alerts.url}")
    private String urlAlertsByCarId;


    @Override
    public GarageDto convert(MappingContext<Garage, GarageDto> mappingContext) {
        Garage garage = mappingContext.getSource();
        GarageDto garageDto = GarageDto.builder()
                .id(garage.getId())
                .name(garage.getName())
                .capacity(garage.getCapacity())
                .isAllowedLpg(garage.isAllowedLpg())
                .cars(mapCarsToCarsDto(garage.getCars()))
                .build();
        garageDto.add(Link.of(urlAlertsByGarageId + garageDto.getId(), "alerts"));
        garageDto.getCars().forEach(c -> c.add(Link.of(urlAlertsByCarId + c.getId(), "alerts")));
        return garageDto;
    }


    private Set<CarDto> mapCarsToCarsDto(Set<Car> cars){
        return cars.stream()
                .map(car -> new CarDto(car.getId(), car.getMark(),
                        car.getModel(), car.getYearOfProduction(),
                        car.getDriveType(), car.getVin()))
                .collect(Collectors.toSet());
    }
}
