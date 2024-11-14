package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address.AddressResponse;

public record PatientResponse(
        Integer id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        AddressResponse primaryAddress
        ) {
}
