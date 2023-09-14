package com.ADALocateCar.services.rent.vehiclePickUp;

import com.ADALocateCar.models.Rent;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record RentVehiclePickUpResponseDTO(
        Long rentId,
        Long customerId,
        Long vehicleId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT") Date withdrawDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT") Date returnDate,
        String pickUpLocation,
        String returnLocation,
        Double paymentValue
) {
    public RentVehiclePickUpResponseDTO(Rent rent) {
        this(
                rent.getId(),
                rent.getCustomerId(),
                rent.getVehicleId(),
                rent.getWithdrawDate(),
                rent.getReturnDate(),
                rent.getPickUpLocation(),
                rent.getReturnLocation(),
                rent.getPaymentValue()
        );
    }
}