package com.ADALocateCar.controllers;

import com.ADALocateCar.models.Vehicle;
import com.ADALocateCar.repositories.VehicleRepository;
import com.ADALocateCar.services.vehicle.VehicleRequestDTO;
import com.ADALocateCar.services.vehicle.VehicleResponseDTO;
import com.ADALocateCar.services.vehicle.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<String> createVehicle(@RequestBody @Valid VehicleRequestDTO data) {
        try {
            boolean create = vehicleService.createVehicle(data);
            if (!create) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Veículo já existe");
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o recurso");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<VehicleResponseDTO> getAll() {
        return vehicleRepository.findAll().stream().map(VehicleResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/model/{nameModel}")
    public ResponseEntity<List<VehicleResponseDTO>> getVehiclesByNameModel(@PathVariable String nameModel) {
        List<Vehicle> vehicles = vehicleRepository.findByModelContainingIgnoreCase(nameModel);

        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<VehicleResponseDTO> responseDTOs = vehicles.stream().map(VehicleResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable String id, @RequestBody @Valid VehicleRequestDTO data) {
        boolean updated = vehicleService.updateVehicle(id, data);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable String id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }
}
