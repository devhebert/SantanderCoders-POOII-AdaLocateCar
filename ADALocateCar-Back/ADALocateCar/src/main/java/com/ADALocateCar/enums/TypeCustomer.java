package com.ADALocateCar.enums;

public enum TypeCustomer {
    CPF,
    CNPJ;

    public String getTypeCustomer(TypeCustomer typeCustomer) {
        return switch (typeCustomer) {
            case CPF  -> "Pessoa Física";
            case CNPJ -> "Pessoa Jurídica";
            default     -> "Desconhecido";
        };
    }
}
