package com.ADALocateCar.controllers;

import com.ADALocateCar.models.Rent;
import com.ADALocateCar.repositories.RentRepository;
import com.ADALocateCar.services.rent.dashboard.RentDashboardView;
import com.ADALocateCar.services.rent.dashboard.RentDashboardDTO;
import com.ADALocateCar.services.rent.vehiclePickUp.RentVehiclePickUpRequestDTO;
import com.ADALocateCar.services.rent.vehiclePickUp.RentVehiclePickUpResponseDTO;
import com.ADALocateCar.services.rent.vehiclePickUp.RentVehiclePickUpService;
import com.ADALocateCar.services.rent.vehicleReturn.RentVehicleReturnRequestDTO;
import com.ADALocateCar.services.rent.vehicleReturn.RentVehicleReturnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("rent")
public class RentController {
    @Autowired
    private RentVehiclePickUpService rentVehiclePickUpService;

    @Autowired
    private RentVehicleReturnService rentVehicleReturnService;

    @Autowired
    private RentDashboardView dashboardView;

    @Autowired
    private RentRepository rentRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<String> createRentVehiclePickUp(@RequestBody @Valid RentVehiclePickUpRequestDTO data) {
        try {
            boolean create = rentVehiclePickUpService.pickUp(data);
            if (!create) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Aluguel já existe");
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o recurso");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<RentVehiclePickUpResponseDTO> getAll() {
        List<Rent> rentList = rentRepository.findAll();

        return rentList.stream().map(RentVehiclePickUpResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/return")
    public ResponseEntity<String> createRentVehicleReturn(@RequestBody @Valid RentVehicleReturnRequestDTO data) {
        try {
            boolean create = rentVehicleReturnService.returnVehicle(data);
            if (!create) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Aluguel já existe");
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o recurso");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable String id) {
        boolean deleted = dashboardView.deleteRent(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/dashboard")
    public List<RentDashboardDTO> getInformationRentDashboardView() {
        List<Map<String, Object>> rentList = dashboardView.getRentInformation();

        List<RentDashboardDTO> euiList = new ArrayList<>();

        if (rentList == null) {
            return Collections.emptyList();
        }

        for (Map<String, Object> result : rentList) {
            Integer customerId = (Integer) result.get("customer_id");
            Integer rentId = (Integer) result.get("rent_id");
            Integer vehicleId = (Integer) result.get("vehicle_id");
            Date returnDate = (Date) result.get("return_date");
            Date withdrawDate = (Date) result.get("withdraw_date");
            String pickUpLocation = (String) result.get("pick_up_location");
            String returnLocation = (String) result.get("return_location");
            String fullname = (String) result.get("fullname");
            String cpfCnpj = (String) result.get("cpf_cnpj");
            String plate = (String) result.get("plate");
            String model = (String) result.get("model");
            String paymentValue = (String) result.get("payment_value");

            if (customerId != null && rentId != null && vehicleId != null && returnDate != null
                    && withdrawDate != null && pickUpLocation != null && returnLocation != null
                    && fullname != null && cpfCnpj != null && plate != null && model != null) {
                RentDashboardDTO eui = new RentDashboardDTO(
                        customerId.intValue(),
                        rentId.intValue(),
                        vehicleId.intValue(),
                        returnDate,
                        withdrawDate,
                        pickUpLocation,
                        returnLocation,
                        fullname,
                        cpfCnpj,
                        plate,
                        model,
                        paymentValue
                );
                euiList.add(eui);
            }
        }

        return euiList;
    }
}
