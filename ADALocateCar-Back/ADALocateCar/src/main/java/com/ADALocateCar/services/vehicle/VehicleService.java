package com.ADALocateCar.services.vehicle;

import com.ADALocateCar.models.Customer;
import com.ADALocateCar.models.Vehicle;
import com.ADALocateCar.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public boolean createVehicle(VehicleRequestDTO data) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findByPlate(data.plate());
        if (existingVehicle.isPresent()) {
            return false;
        }

        Vehicle vehicle = new Vehicle(data);
        vehicleRepository.save(vehicle);
        return true;
    }

    public boolean updateVehicle(String id, VehicleRequestDTO data) {
        Long convertedId = Long.parseLong(id);
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(convertedId);
        if (existingVehicle.isEmpty()) {
            return false;
        }

        Vehicle vehicle = existingVehicle.get();
        vehicle.setYear(data.year() != null ? data.year() : vehicle.getYear());
        vehicle.setColor(data.color() != null ? data.color() : vehicle.getColor());
        vehicle.setModel(data.model() != null ? data.model() : vehicle.getModel());
        vehicle.setPlate(data.plate() != null ? data.plate() : vehicle.getPlate());
        vehicle.setBrand(data.brand() != null ? data.brand() : vehicle.getBrand());
        vehicle.setTypeVehicle(data.typeVehicle() != null ? data.typeVehicle() : vehicle.getTypeVehicle());
        vehicle.setStatusVehicle(data.statusVehicle() != null ? data.statusVehicle() : vehicle.getStatusVehicle());

        vehicleRepository.save(vehicle);
        return true;
    }

    public boolean deleteVehicle(String id) {
        Long convertedId = Long.parseLong(id);
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(convertedId);

        if (existingVehicle.isEmpty()) {
            return false;
        }

        vehicleRepository.delete(existingVehicle.get());
        return true;
    }
}
