package com.example.test04system1.dto;

import com.example.test04system1.model.DriveType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class CarDto extends RepresentationModel<CarDto> {

    private Long id;
    private String mark;
    private String model;
    private int yearOfProduction;
    private DriveType driveType;
    @EqualsAndHashCode.Include
    private String vin;

}
