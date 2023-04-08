package com.example.test04system1.domain;

import com.example.test04system1.model.DriveType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mark;
    private String model;
    private int yearOfProduction;
    @Enumerated(EnumType.STRING)
    private DriveType driveType;
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
    private String vin;
    @ManyToOne
    @ToString.Exclude
    private Garage garage;
}
