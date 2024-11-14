package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address;

public record AddressResponse(
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode
    ) {
}
