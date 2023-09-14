package com.ADALocateCar.enums;

public enum StatusVehicle {
    AVAILABLE,
    UNAVAILABLE;

    public String getStatusVehicleName(StatusVehicle statusVehicle) {
        return switch (statusVehicle) {
            case AVAILABLE  -> "Disponível";
            case UNAVAILABLE -> "Indisponível";
            default     -> "Desconhecido";
        };
    }
}
