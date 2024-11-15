package com.ebm.findmycar.controller;

// Importation des classes nécessaires
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

// Déclare cette classe comme un contrôleur REST pour gérer les requêtes HTTP
@RestController
// Permet les requêtes CORS depuis une URL spécifique (ici l'application front-end à localhost:8081)
@CrossOrigin(origins = "http://localhost:8081")
// Définit la route de base pour tous les endpoints de ce contrôleur
@RequestMapping("/vehicles")
// Ajoute des métadonnées pour Swagger, permettant de documenter les endpoints
@Tag(name = "Vehicles", description = "Endpoints for managing vehicles")
public class VehicleController {

    // Service qui contient la logique métier pour les véhicules
    private final VehicleService vehicleService;

    // Injection du service via le constructeur (meilleure pratique pour les tests et la maintenabilité)
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Endpoint pour ajouter un nouveau véhicule
    @PostMapping
    @Operation(summary = "Add a new vehicle", description = "Create a new vehicle entry in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vehicle created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        // Appelle le service pour sauvegarder un véhicule dans la base de données
        return vehicleService.save(vehicle);
    }

    // Endpoint pour récupérer tous les véhicules
    @GetMapping
    @Operation(summary = "Get all vehicles", description = "Retrieve all vehicles from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicles retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    public List<Vehicle> getAllVehicles() {
        // Appelle le service pour obtenir la liste de tous les véhicules
        return vehicleService.getAllVehicles();
    }

    // Endpoint pour récupérer un véhicule par son ID
    @GetMapping("/{id}")
    @Operation(summary = "Get vehicle by ID", description = "Retrieve a vehicle by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle found"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public Optional<Vehicle> getVehicleById(@PathVariable int id) {
        // Appelle le service pour récupérer un véhicule spécifique via son ID
        return vehicleService.getVehicleById(id);
    }

    // Endpoint pour mettre à jour un véhicule existant
    @PutMapping("/{id}")
    @Operation(summary = "Update a vehicle", description = "Update an existing vehicle")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vehicle updated successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public Vehicle updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicleDetails) {
        // Appelle le service pour mettre à jour les détails d’un véhicule
        return vehicleService.updateVehicle(id, vehicleDetails);
    }

    // Endpoint pour supprimer un véhicule via son ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a vehicle", description = "Delete a vehicle by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vehicle deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public void deleteVehicle(@PathVariable int id) {
        // Appelle le service pour supprimer un véhicule
        vehicleService.deleteVehicle(id);
    }

    // Endpoint pour récupérer uniquement les voitures
    @GetMapping("/cars")
    @Operation(summary = "Get all cars", description = "Retrieve all cars from the database")
    public List<Car> getAllCars() {
        // Appelle le service pour récupérer tous les véhicules de type "Car"
        return vehicleService.getAllCars();
    }

    // Endpoint pour récupérer uniquement les motos
    @GetMapping("/motorcycles")
    @Operation(summary = "Get all motorcycles", description = "Retrieve all motorcycles from the database")
    public List<Motorcycle> getAllMotorcycles() {
        // Appelle le service pour récupérer tous les véhicules de type "Motorcycle"
        return vehicleService.getAllMotorcycles();
    }

    // Endpoint pour récupérer uniquement les véhicules utilitaires
    @GetMapping("/utility-vehicles")
    @Operation(summary = "Get all utility vehicles", description = "Retrieve all utility vehicles from the database")
    public List<UtilityVehicle> getAllUtilityVehicles() {
        // Appelle le service pour récupérer tous les véhicules de type "UtilityVehicle"
        return vehicleService.getAllUtilityVehicles();
    }
}