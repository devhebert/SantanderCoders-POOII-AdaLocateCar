package com.ADALocateCar.services.rent.vehiclePickUp;

import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.enums.TypeVehicle;

import java.util.Date;

public record RentVehiclePickUpRequestDTO(Long customerId, Long vehicleId, Date withdrawDate, Date returnDate, String pickUpLocation, String returnLocation, StatusVehicle statusVehicle, TypeVehicle typeVehicle) {
}
