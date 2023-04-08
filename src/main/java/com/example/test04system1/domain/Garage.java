package com.example.test04system1.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    private boolean isAllowedLpg;
    @ToString.Exclude
    @OneToMany(mappedBy = "garage")
    private Set<Car> cars;

    public void addCar(Car car){
        cars.add(car);
        car.setGarage(this);
    }

    public void removeCar(Car car){
        cars.remove(car);
        car.setGarage(null);
    }

    public int getNumberOfFreeParkingPlaces(){
        return this.capacity - this.cars.size();
    }


}
