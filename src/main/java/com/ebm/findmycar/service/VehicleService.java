package com.ebm.findmycar.service;

import com.ebm.findmycar.model.Car;
import com.ebm.findmycar.model.Motorcycle;
import com.ebm.findmycar.model.UtilityVehicle;
import com.ebm.findmycar.model.Vehicle;
import com.ebm.findmycar.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    // Ajouter un véhicule
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Obtenir tous les véhicules
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Obtenir un véhicule par ID
    public Optional<Vehicle> getVehicleById(int id) {
        return vehicleRepository.findById(id);
    }

    // Mettre à jour un véhicule
    public Vehicle updateVehicle(int id, Vehicle vehicleDetails) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setId(id);
            vehicle.setBrand(vehicleDetails.getBrand());
            vehicle.setModel(vehicleDetails.getModel());
            vehicle.setColor(vehicleDetails.getColor());
            vehicle.setFiscal_power(vehicleDetails.getFiscal_power());
            vehicle.setBase_price(vehicleDetails.getBase_price());
            vehicle.setKm_price(vehicleDetails.getKm_price());
            vehicle.setMatriculation(vehicleDetails.getMatriculation());
            return vehicleRepository.save(vehicle);
        }).orElseThrow(()->new RuntimeException("Vehicle not found"));
    }

    // Supprimer un véhicule par son ID
    public void deleteVehicle(int id) {
        vehicleRepository.deleteById(id);
    }

    public List<Car> getAllCars() {
        return vehicleRepository.findAllCars();
    }

    public List<Motorcycle> getAllMotorcycles() {
        return vehicleRepository.findAllMotorcycles();
    }

    public List<UtilityVehicle> getAllUtilityVehicles() {
        return vehicleRepository.findAllUtilityVehicles();
    }

}
