package com.ADALocateCar.repositories;

import com.ADALocateCar.models.Rent;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RentRepository extends JpaRepository<Rent, Long> {
}
