package com.example.test04system1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GarageDto extends RepresentationModel<GarageDto> {

    private Long id;
    private String name;
    private int capacity;
    private boolean isAllowedLpg;
    private Set<CarDto> cars;

}
