package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientResponse2;

public record AddressResponse2(
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode,
        PatientResponse2 patient
) {
}
