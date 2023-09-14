package com.ADALocateCar.services;

import com.ADALocateCar.enums.TypeCustomer;
import com.ADALocateCar.enums.TypeVehicle;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class RentalPriceCalculatorService {
    public Double calculatePricePerDaily(Date withdrawDate, Date returnDate, TypeVehicle typeVehicle, TypeCustomer typeCustomer) {
        Integer withdrawDay = Integer.parseInt(new SimpleDateFormat("dd").format(withdrawDate));
        Integer returnDay = Integer.parseInt(new SimpleDateFormat("dd").format(returnDate));

        Integer difference = Math.abs(returnDay - withdrawDay);

        Double dailyRate = 0.0;

        switch (typeVehicle) {
            case SMALL  -> dailyRate = 100.00;
            case MEDIUM -> dailyRate = 150.00;
            case SUV    -> dailyRate = 200.00;
            default     -> {
                return 0.0;
            }
        }

        return calculateDiscount(typeCustomer, difference, dailyRate);
    }

    public Double calculatePricePerDailyLate(Date returnDate, Date realReturnDate, Double totalValue, TypeVehicle typeVehicle) {
        Integer returnDay = Integer.parseInt(new SimpleDateFormat("dd").format(returnDate));
        Integer realReturnDay = Integer.parseInt(new SimpleDateFormat("dd").format(realReturnDate));

        Integer difference = Math.abs(returnDay - realReturnDay);

        Double dailyRate = 0.0;

        switch (typeVehicle) {
            case SMALL  -> dailyRate = 100.00;
            case MEDIUM -> dailyRate = 150.00;
            case SUV    -> dailyRate = 200.00;
            default     -> {
                return 0.0;
            }
        }

        return (difference * dailyRate) + totalValue;
    }

    private static Double calculateDiscount(TypeCustomer typeCustomer, Integer difference, Double dailyRate) {
        Double totalPayment = difference * dailyRate;

        if(difference >= 5 && typeCustomer == TypeCustomer.CPF) {
            totalPayment = totalPayment - (totalPayment * 0.05);
        }

        if(difference >= 3 && typeCustomer == TypeCustomer.CNPJ) {
            totalPayment = totalPayment - (totalPayment * 0.10);
        }

        return totalPayment;
    }
}
