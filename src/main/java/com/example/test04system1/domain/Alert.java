package com.example.test04system1.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Alert  {

    private String ip;
    private LocalDate date;
    private long carId;
    private long garageId;
}
