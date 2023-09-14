package com.ADALocateCar.services.customer;

import com.ADALocateCar.models.Customer;
import com.ADALocateCar.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean createCustomer(CustomerRequestDTO data) {
        Optional<Customer> existingCustomer = customerRepository.findByCpfCnpj(data.cpfCnpj());
        if (existingCustomer.isPresent()) {
            return false;
        }

        Customer customer = new Customer(data);
        customerRepository.save(customer);
        return true;
    }

    public boolean updateCustomer(String id, CustomerRequestDTO data) {
        Long convertedId = Long.parseLong(id);
        Optional<Customer> existingCustomer = customerRepository.findById(convertedId);
        if (existingCustomer.isEmpty()) {
            return false;
        }

        Customer customer = existingCustomer.get();
        customer.setFullname(data.fullname() != null ? data.fullname() : customer.getFullname());
        customer.setTypeCustomer(data.typeCustomer() != null ? data.typeCustomer() : customer.getTypeCustomer());
        customer.setCpfCnpj(data.cpfCnpj() != null ? data.cpfCnpj() : customer.getCpfCnpj());
        customer.setBirthDate(data.birthDate() != null ? data.birthDate() : customer.getBirthDate());
        customer.setPhone(data.phone() != null ? data.phone() : customer.getPhone());
        customer.setAddress(data.address() != null ? data.address() : customer.getAddress());

        customerRepository.save(customer);
        return true;
    }

    public boolean deleteCustomer(String id) {
        Long convertedId = Long.parseLong(id);
        Optional<Customer> existingCustomer = customerRepository.findById(convertedId);

        if (existingCustomer.isEmpty()) {
            return false;
        }

        customerRepository.delete(existingCustomer.get());
        return true;
    }
}
