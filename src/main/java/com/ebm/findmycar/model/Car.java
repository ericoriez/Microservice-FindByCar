package com.ebm.findmycar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("CAR")
@Getter
@Setter

public class Car extends Vehicle {
    public Car() {
        super();
    }
    public Car(String brand, String model, String color,int fiscal_power, float base_price, float km_price, String matriculation) {
        super(brand, model, color,fiscal_power, base_price, km_price, matriculation);
    }
}
