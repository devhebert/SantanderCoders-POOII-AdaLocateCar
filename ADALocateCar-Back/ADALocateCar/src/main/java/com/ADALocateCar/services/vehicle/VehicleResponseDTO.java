package com.ADALocateCar.services.vehicle;


import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.enums.TypeVehicle;
import com.ADALocateCar.models.Vehicle;

public record VehicleResponseDTO(Long id, String plate, Integer year, String model, String color, String brand, TypeVehicle typeVehicle, StatusVehicle statusVehicle) {
    public VehicleResponseDTO(Vehicle vehicle){
        this(vehicle.getId(), vehicle.getPlate(), vehicle.getYear(), vehicle.getModel(), vehicle.getColor(), vehicle.getBrand(), vehicle.getTypeVehicle(), vehicle.getStatusVehicle());
    }
}
