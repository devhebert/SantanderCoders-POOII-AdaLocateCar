package com.ADALocateCar.models;

import com.ADALocateCar.enums.StatusVehicle;
import com.ADALocateCar.enums.TypeVehicle;
import com.ADALocateCar.services.vehicle.VehicleRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plate;
    @Column(name = "ano")
    private Integer year;
    private String model;
    private String color;
    private String brand;
    private TypeVehicle typeVehicle;
    private StatusVehicle statusVehicle;

    public Vehicle(VehicleRequestDTO data) {
        this.plate = data.plate();
        this.year = data.year();
        this.model = data.model();
        this.color = data.color();
        this.brand = data.brand();
        this.typeVehicle = data.typeVehicle();
        this.statusVehicle = (data.statusVehicle() != null) ? data.statusVehicle() : StatusVehicle.AVAILABLE;
    }
}
