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
        try {
            return vehicleRepository.findById(id).map(vehicle -> {
                // Mise à jour des attributs communs
                vehicle.setBrand(vehicleDetails.getBrand());
                vehicle.setModel(vehicleDetails.getModel());
                vehicle.setColor(vehicleDetails.getColor());
                vehicle.setFiscalPower(vehicleDetails.getFiscalPower());
                vehicle.setBasePrice(vehicleDetails.getBasePrice());
                vehicle.setKmPrice(vehicleDetails.getKmPrice());
                vehicle.setMatriculation(vehicleDetails.getMatriculation());

                // Vérification et mise à jour des attributs spécifiques
                if (vehicle instanceof Motorcycle && vehicleDetails instanceof Motorcycle) {
                    ((Motorcycle) vehicle).setEngineCapacityCm3(((Motorcycle) vehicleDetails).getEngineCapacityCm3());
                } else if (vehicle instanceof UtilityVehicle && vehicleDetails instanceof UtilityVehicle) {
                    ((UtilityVehicle) vehicle).setVolumeCapacity(((UtilityVehicle) vehicleDetails).getVolumeCapacity());
                } else if (vehicle instanceof Car && vehicleDetails instanceof Car) {
                    // Pas de champ spécifique pour Car, on continue la mise à jour normale
                } else {
                    // Si le type ne correspond pas, lancer une exception
                    throw new IllegalArgumentException("Vehicle type mismatch: Expected "
                            + vehicle.getClass().getSimpleName()
                            + " but received "
                            + vehicleDetails.getClass().getSimpleName());
                }

                // Sauvegarde du véhicule mis à jour
                return vehicleRepository.save(vehicle);
            }).orElseThrow(() -> new RuntimeException("Vehicle not found"));
        } catch (Exception e) {
            // Log de l'erreur
            System.err.println("Error updating vehicle with ID: " + id + ". Details: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error updating vehicle: " + e.getMessage());
        }
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
