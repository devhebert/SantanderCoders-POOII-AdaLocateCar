package com.ADALocateCar.models;

import com.ADALocateCar.enums.TypeCustomer;
import com.ADALocateCar.services.customer.CustomerRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "customer")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String fullname;
    private TypeCustomer typeCustomer;
    private String cpfCnpj;
    private Date birthDate;
    private String phone;
    private String address;

    public Customer(CustomerRequestDTO data) {
        this.fullname = data.fullname();
        this.birthDate = data.birthDate();
        this.address = data.address();
        this.typeCustomer = data.typeCustomer();
        this.cpfCnpj = data.cpfCnpj();
        this.phone = data.phone();
    }
}
