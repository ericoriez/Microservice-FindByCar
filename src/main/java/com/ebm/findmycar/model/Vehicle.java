package com.ebm.findmycar.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Car.class, name = "Car"),
        @JsonSubTypes.Type(value = Motorcycle.class, name = "Motorcycle"),
        @JsonSubTypes.Type(value = UtilityVehicle.class, name = "UtilityVehicle")
})

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String brand;
    private String model;
    private String color;
    private int fiscalPower;
    private float basePrice;
    private float kmPrice;
    private String matriculation;
    private String image;


    public Vehicle(){}

    public Vehicle(String brand, String model, String color, int fiscalPower, float basePrice, float kmPrice, String matriculation, String image) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fiscalPower = fiscalPower;
        this.basePrice = basePrice;
        this.kmPrice = kmPrice;
        this.matriculation = matriculation;
        this.image = image;
    }

}
