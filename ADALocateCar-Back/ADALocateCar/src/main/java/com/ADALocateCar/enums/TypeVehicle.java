package com.ADALocateCar.enums;

public enum TypeVehicle {
    SMALL,
    MEDIUM,
    SUV;

    public String getTypeVehicleName(TypeVehicle typeVehicle) {
        return switch (typeVehicle) {
            case SMALL  -> "Pequeno";
            case MEDIUM -> "Médio";
            case SUV    -> "SUV";
            default     -> "Desconhecido";
        };
    }
}
