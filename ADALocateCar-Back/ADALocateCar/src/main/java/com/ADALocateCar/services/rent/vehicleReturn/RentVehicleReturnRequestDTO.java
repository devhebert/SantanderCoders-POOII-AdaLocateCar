package com.ADALocateCar.services.rent.vehicleReturn;

import java.util.Date;

public record RentVehicleReturnRequestDTO(Long rentId, Long customerId, Long vehicleId, Double paymentValue,
                                          String pickUpLocation, String returnLocation, Date returnDate,
                                          Date withdrawDate, Date realReturnDate) {
}
