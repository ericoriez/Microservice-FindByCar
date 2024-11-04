package com.ebm.findmycar.repository;

import com.ebm.findmycar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}
