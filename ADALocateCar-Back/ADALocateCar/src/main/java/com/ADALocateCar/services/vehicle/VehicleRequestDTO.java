package com.ADALocateCar.services.vehicle;

import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.enums.TypeVehicle;

public record VehicleRequestDTO(String id, String plate, Integer year, String model, String color, String brand, TypeVehicle typeVehicle, StatusVehicle statusVehicle) {
}