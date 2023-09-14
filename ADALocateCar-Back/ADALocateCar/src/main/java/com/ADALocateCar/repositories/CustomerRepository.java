package com.ADALocateCar.repositories;

import com.ADALocateCar.enums.TypeCustomer;
import com.ADALocateCar.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCpfCnpj(String cpfCnpj);
}
