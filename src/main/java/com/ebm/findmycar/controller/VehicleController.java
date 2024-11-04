package com.ebm.findmycar.controller;

import com.ebm.findmycar.model.Car;
import com.ebm.findmycar.model.Motorcycle;
import com.ebm.findmycar.model.UtilityVehicle;
import com.ebm.findmycar.model.Vehicle;
import com.ebm.findmycar.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable int id) {
        return vehicleService.getVehicleById(id);
    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.updateVehicle(id, vehicleDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return vehicleService.getAllCars();
    }

    @GetMapping("/motorcycles")
    public List<Motorcycle> getAllMotorcycles() {
        return vehicleService.getAllMotorcycles();
    }

    @GetMapping("/utility-vehicles")
    public List<UtilityVehicle> getAllUtilityVehicles() {
        return vehicleService.getAllUtilityVehicles();
    }
}
