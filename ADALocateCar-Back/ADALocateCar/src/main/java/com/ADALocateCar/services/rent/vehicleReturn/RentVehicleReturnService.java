package com.ADALocateCar.services.rent.vehicleReturn;

import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.interfaces.UpdateVehicleStatus;
import com.ADALocateCar.models.Rent;
import com.ADALocateCar.models.Vehicle;
import com.ADALocateCar.repositories.CustomerRepository;
import com.ADALocateCar.repositories.RentRepository;
import com.ADALocateCar.repositories.VehicleRepository;
import com.ADALocateCar.services.RentalPriceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentVehicleReturnService implements UpdateVehicleStatus {
    private final RentRepository rentRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    RentalPriceCalculatorService rentalPriceCalculatorService;

    @Autowired
    public RentVehicleReturnService(RentRepository rentRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public boolean returnVehicle(RentVehicleReturnRequestDTO data) {
        Optional<Rent> existingRent = rentRepository.findById(data.rentId());
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(data.vehicleId());
        
        if (existingRent.isEmpty()) {
            return false;
        }

        Vehicle vehicle = vehicleOptional.get();

        changeVehicleStatus(vehicle);

        Double updatedPaymentValue = rentalPriceCalculatorService.calculatePricePerDailyLate(data.returnDate(), data.realReturnDate(), data.paymentValue(), vehicle.getTypeVehicle());
        
        Rent rent = existingRent.get();
        rent.setVehicleId(data.vehicleId() != null ? data.vehicleId() : rent.getVehicleId());
        rent.setCustomerId(data.customerId() != null ? data.customerId() : rent.getCustomerId());
        rent.setPickUpLocation(data.pickUpLocation() != null ? data.pickUpLocation() : rent.getPickUpLocation());
        rent.setReturnLocation(data.returnLocation() != null ? data.returnLocation() : rent.getReturnLocation());
        rent.setReturnDate(data.returnDate() != null ? data.returnDate() : rent.getReturnDate());
        rent.setWithdrawDate(data.withdrawDate() != null ? data.withdrawDate() : rent.getWithdrawDate());
        rent.setPaymentValue(data.paymentValue() != null ? updatedPaymentValue : rent.getPaymentValue());
        rentRepository.save(rent);

        return true;
    }

    @Override
    public void changeVehicleStatus(Vehicle vehicle) {
        if(vehicle.getStatusVehicle() == StatusVehicle.UNAVAILABLE) {
            vehicle.setStatusVehicle(StatusVehicle.AVAILABLE);
            vehicleRepository.save(vehicle);
        }
    }
}

