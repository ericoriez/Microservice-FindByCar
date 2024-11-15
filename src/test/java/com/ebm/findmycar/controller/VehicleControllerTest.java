package com.ebm.findmycar.controller;

import com.ebm.findmycar.model.Car;
import com.ebm.findmycar.model.Motorcycle;
import com.ebm.findmycar.model.Vehicle;
import com.ebm.findmycar.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void addVehicleTest() throws Exception {
        // Création d'un objet Vehicle de type Car avec des données d'exemple
        Vehicle vehicle = new Car("Toyota", "Corolla", "Red", 75, 100, 2, "abcdef","./images/car.png");

        // Mock du service pour qu'il renvoie le véhicule créé quand la méthode save est appelée
        when(vehicleService.save(any(Vehicle.class))).thenReturn(vehicle);

        // Envoi d'une requête POST pour ajouter un véhicule
        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicle)))
                .andExpect(status().isOk())// Vérifie que le statut de la réponse est 200 OK
                .andExpect(jsonPath("$.brand").value("Toyota"))// Vérifie que la marque est "Toyota"
                .andExpect(jsonPath("$.model").value("Corolla")) // Vérifie que le modèle est "Corolla"
                .andExpect(jsonPath("$.color").value("Red"));// Vérifie que la couleur est "Red"

    }


    @Test
    public void getAllVehiclesTest() throws Exception {

        // Création d'une liste de véhicules pour simuler une réponse
        Vehicle vehicle1 = new Car("Toyota", "Corolla", "Red", 75, 100, 2, "abcdef","./images/vehicle1.png");
        Vehicle vehicle2 = new Car("Renault", "Clio", "Pink", 75, 100, 2, "abcdef","./images/vehicle2.png");
        Vehicle vehicle3 = new Motorcycle("Yamaha", "R1", "Pink", 75, 100, 2, "abcdef",998,"./images/vehicle3.png");
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2, vehicle3);

        // Mock du service pour qu'il renvoie la liste des véhicules
        when(vehicleService.getAllVehicles()).thenReturn(vehicles);

        // Envoi d'une requête GET pour obtenir tous les véhicules
        mockMvc.perform(get("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].brand").value("Toyota"))
                .andExpect(jsonPath("$[1].brand").value("Renault"))
                .andExpect(jsonPath("$[2].brand").value("Yamaha"));

    }


    @Test
    public void getVehicleByIdTest() throws Exception {

        // Création d'un véhicule pour simuler le retour d'un véhicule par ID
        Vehicle vehicle = new Car("Toyota", "Corolla", "Red", 75, 100, 2, "abcdef","./images/car.png");

        // Simulation du retour d'un véhicule spécifique par le service
        when(vehicleService.getVehicleById(1)).thenReturn(Optional.of(vehicle));


        // Exécution de la requête GET vers /vehicles/1
        mockMvc.perform(get("/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Toyota"))
                .andExpect(jsonPath("$.model").value("Corolla"))
                .andExpect(jsonPath("$.color").value("Red"));

    }


    @Test
    public void deleteVehicleTest() throws Exception {
        // Exécution de la requête DELETE vers /vehicles/1
        mockMvc.perform(delete("/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Vérifie que le statut de réponse est 200 OK
        // Remarque : Ici, il n'est pas nécessaire de simuler le service car nous ne retournons rien
        // dans le contrôleur après une suppression.
    }



    @Test
    public void getAllCarsTest() throws Exception {

        // Création de deux objets Car avec des attributs différents
        Vehicle car1 = new Car("Toyota", "Corolla", "Red", 75, 100, 2, "abcdef", "./images/car.png");
        Vehicle car2 = new Car("Renault", "Clio", "Pink", 75, 100, 2, "abcdef", "./images/car.png");

        // Création d'une liste contenant ces deux voitures
        List<Car> cars = Arrays.asList((Car) car1, (Car) car2);

        // Simulation de la méthode getAllCars() pour retourner la liste de voitures
        when(vehicleService.getAllCars()).thenReturn(cars);

        // Envoi d'une requête GET à l'endpoint /vehicles/cars
        mockMvc.perform(get("/vehicles/cars")
                        .contentType(MediaType.APPLICATION_JSON))// Indication que la requête attend un contenu JSON
                .andExpect(status().isOk())// Vérification que le statut de la réponse est bien 200 (OK)
                .andExpect(jsonPath("$[0].brand").value("Toyota"))// Vérification que la première voiture a pour marque "Toyota"
                .andExpect(jsonPath("$[1].brand").value("Renault"));// Vérification que la deuxième voiture a pour marque "Renault"
    }


    @Test
    public void updateVehicleTest() throws Exception {

        // Création d'un objet Car initial avant modification
        Vehicle originalVehicle = new Car("Toyota", "Corolla", "Red", 75, 100, 2, "ABC123","./images/car.png");

        // Création d'un objet Car modifié (ici, seule la couleur change)
        Vehicle updatedVehicle = new Car("Toyota", "Corolla", "Blue", 75, 100, 2, "ABC123","./images/car.png");

        // Simulation de la méthode getVehicleById() pour retourner l'objet original en fonction de l'ID
        when(vehicleService.getVehicleById(1)).thenReturn(Optional.of(originalVehicle));

        // Simulation de la méthode updateVehicle() pour retourner l'objet modifié après la mise à jour
        when(vehicleService.updateVehicle(eq(1), any(Vehicle.class))).thenReturn(updatedVehicle);

        // Envoi d'une requête PUT à l'endpoint /vehicles/1 avec les informations de la voiture modifiée
        mockMvc.perform(put("/vehicles/1")
                        .contentType(MediaType.APPLICATION_JSON)// Indication que la requête contient des données en JSON
                        .content(objectMapper.writeValueAsString(updatedVehicle)))// Conversion de l'objet en JSON
                .andExpect(status().isOk())// Vérification que le statut de la réponse est bien 200 (OK)
                .andExpect(jsonPath("$.color").value("Blue"));// Vérification que la couleur de la voiture est bien "Blue" après la mise à jour
    }
}
