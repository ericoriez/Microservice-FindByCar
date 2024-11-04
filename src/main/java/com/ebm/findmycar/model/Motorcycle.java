package com.ebm.findmycar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@DiscriminatorValue("MOTORCYCLE")
public class Motorcycle extends Vehicle {

    private int engineCapacityCm3;

    public Motorcycle() {
        super();
    }

    public Motorcycle(String brand, String model, String color,int fiscal_power, float base_price, float km_price, String matriculation, int engineCapacityCm3) {
        super(brand, model, color,fiscal_power, base_price, km_price, matriculation);
        this.engineCapacityCm3 = engineCapacityCm3;
    }
}
