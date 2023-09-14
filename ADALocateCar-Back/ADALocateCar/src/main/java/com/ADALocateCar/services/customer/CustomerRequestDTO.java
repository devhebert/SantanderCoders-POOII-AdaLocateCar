package com.ADALocateCar.services.customer;

import com.ADALocateCar.enums.TypeCustomer;

import java.util.Date;

public record CustomerRequestDTO(String fullname, Date birthDate, String address, TypeCustomer typeCustomer, String cpfCnpj, String phone) {
}
