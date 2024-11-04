package com.ebm.findmycar.repository;

import com.ebm.findmycar.model.Car;
import com.ebm.findmycar.model.Motorcycle;
import com.ebm.findmycar.model.UtilityVehicle;
import com.ebm.findmycar.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    @Query("SELECT v FROM Car v")
    List<Car> findAllCars();

    @Query("SELECT v FROM Motorcycle v")
    List<Motorcycle> findAllMotorcycles();

    @Query("SELECT v FROM UtilityVehicle v")
    List<UtilityVehicle> findAllUtilityVehicles();
}
