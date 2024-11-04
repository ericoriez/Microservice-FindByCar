package com.ebm.findmycar.repository;

import com.ebm.findmycar.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Integer> {

}
