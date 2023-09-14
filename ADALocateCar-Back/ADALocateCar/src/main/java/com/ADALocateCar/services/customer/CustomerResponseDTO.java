package com.ADALocateCar.services.customer;

import com.ADALocateCar.enums.TypeCustomer;
import com.ADALocateCar.models.Customer;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public record CustomerResponseDTO(Long id, String fullname, @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT") Date birthDate, String address, TypeCustomer typeCustomer, String cpfCnpj, String phone) {
    public CustomerResponseDTO(Customer customer) {
        this(customer.getId(), customer.getFullname(), customer.getBirthDate(), customer.getAddress(), customer.getTypeCustomer(), customer.getCpfCnpj(), customer.getPhone());
    }
}

