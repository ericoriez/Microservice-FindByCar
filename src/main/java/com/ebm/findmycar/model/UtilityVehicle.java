package com.ebm.findmycar.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("UTILITY")
public class UtilityVehicle extends Vehicle {

    private float volumeCapacity;
    public UtilityVehicle() {}
    public UtilityVehicle(String brand, String model, String color,int fiscal_power,float base_price,float km_price, String matriculation, float volumeCapacity) {
        super(brand, model, color,fiscal_power, base_price, km_price, matriculation);
        this.volumeCapacity = volumeCapacity;
    }
}
