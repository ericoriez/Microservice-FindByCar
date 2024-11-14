package com.ebm.findmycar.controller;

import com.ebm.findmycar.model.Car;
import com.ebm.findmycar.model.Motorcycle;
import com.ebm.findmycar.model.UtilityVehicle;
import com.ebm.findmycar.model.Vehicle;
import com.ebm.findmycar.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/vehicles")
@Tag(name = "Vehicles", description = "Endpoints for managing vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @Operation(summary = "Add a new vehicle", description = "Create a new vehicle entry in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieve all vehicles from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID", description = "Retrieve a vehicle by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public Optional<Vehicle> getVehicleById(@PathVariable int id) {
        return vehicleService.getVehicleById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a vehicle", description = "Update an existing vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicleDetails) {
        return vehicleService.updateVehicle(id, vehicleDetails);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle", description = "Delete a vehicle by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehicle deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public void deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicle(id);
    }

    @GetMapping("/cars")
    @Operation(summary = "Get all cars", description = "Retrieve all cars from the database")
    public List<Car> getAllCars() {
        return vehicleService.getAllCars();
    }

    @GetMapping("/motorcycles")
    @Operation(summary = "Get all motorcycles", description = "Retrieve all motorcycles from the database")
    public List<Motorcycle> getAllMotorcycles() {
        return vehicleService.getAllMotorcycles();
    }

    @GetMapping("/utility-vehicles")
    @Operation(summary = "Get all utility vehicles", description = "Retrieve all utility vehicles from the database")
    public List<UtilityVehicle> getAllUtilityVehicles() {
        return vehicleService.getAllUtilityVehicles();
    }
}
