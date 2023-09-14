package com.ADALocateCar.services.rent.dashboard;

import java.util.Date;

public record RentDashboardDTO(
        Integer rentId,
        Integer customerId,
        Integer vehicleId,
        Date returnDate,
        Date withdrawDate,
        String pickUpLocation,
        String returnLocation,
        String fullname,
        String cpfCnpj,
        String plate,
        String model,
        String paymentValue
) {
}
