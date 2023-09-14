package com.ADALocateCar.controllers;


import com.ADALocateCar.repositories.CustomerRepository;
import com.ADALocateCar.services.customer.CustomerRequestDTO;
import com.ADALocateCar.services.customer.CustomerResponseDTO;
import com.ADALocateCar.services.customer.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequestDTO data) {
        try {
            boolean create = customerService.createCustomer(data);
            if (!create) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente já existe");
            }

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o recurso");
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll().stream().map(CustomerResponseDTO::new).toList();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVehicle(@PathVariable String id, @RequestBody @Valid CustomerRequestDTO data) {
        boolean updated = customerService.updateCustomer(id, data);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

}
