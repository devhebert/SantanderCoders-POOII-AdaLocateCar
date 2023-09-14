package com.ADALocateCar.models;

import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.services.rent.vehiclePickUp.RentVehiclePickUpRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "rent") // Nome da tabela no banco de dados
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long vehicleId;
    private Date withdrawDate;
    private Date returnDate;
    private String pickUpLocation;
    private String returnLocation;
    private StatusVehicle statusVehicle;
    private Double paymentValue;

    public Rent(RentVehiclePickUpRequestDTO data) {
        this.customerId = data.customerId();
        this.vehicleId = data.vehicleId();
        this.withdrawDate = data.withdrawDate();
        this.returnDate = data.returnDate();
        this.pickUpLocation = data.pickUpLocation();
        this.returnLocation = data.returnLocation();
        this.statusVehicle = data.statusVehicle();
    }

    public int calculateDaily() {
        return 1;
    }

    public double calculateDiscounts() {
        return 2.0;
    }


}
