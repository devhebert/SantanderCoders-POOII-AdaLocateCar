package com.ADALocateCar.services.rent.vehiclePickUp;

import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.interfaces.UpdateVehicleStatus;
import com.ADALocateCar.models.Customer;
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
public class RentVehiclePickUpService implements UpdateVehicleStatus {
    private final RentRepository rentRepository;
    private final CustomerRepository customerRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    RentalPriceCalculatorService rentalPriceCalculatorService;

    @Autowired
    public RentVehiclePickUpService(RentRepository rentRepository, CustomerRepository customerRepository, VehicleRepository vehicleRepository) {
        this.rentRepository = rentRepository;
        this.customerRepository = customerRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public boolean pickUp(RentVehiclePickUpRequestDTO data) {
        Optional<Customer> customerOptional = customerRepository.findById(data.customerId());
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(data.vehicleId());

        if (!customerOptional.isPresent() && vehicleOptional.isPresent()) {
            return false;
        }

        Customer customer = customerOptional.get();
        Vehicle vehicle = vehicleOptional.get();

        changeVehicleStatus(vehicle);

        Double paymentValue = rentalPriceCalculatorService.calculatePricePerDaily(data.withdrawDate(), data.returnDate(), data.typeVehicle(), customer.getTypeCustomer());

        Rent rent = new Rent();
        rent.setCustomerId(customer.getId());
        rent.setVehicleId(vehicle.getId());
        rent.setWithdrawDate(data.withdrawDate());
        rent.setReturnDate(data.returnDate());
        rent.setPickUpLocation(data.pickUpLocation());
        rent.setReturnLocation(data.returnLocation());
        rent.setStatusVehicle(data.statusVehicle());
        rent.setPaymentValue(paymentValue);

        rentRepository.save(rent);
        return true;
    }

    @Override
    public void changeVehicleStatus(Vehicle vehicle) {
        if (vehicle.getStatusVehicle() == StatusVehicle.AVAILABLE) {
            vehicle.setStatusVehicle(StatusVehicle.UNAVAILABLE);
            vehicleRepository.save(vehicle);
        }

    }
}
