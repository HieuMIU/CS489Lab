package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address.AddressRequest;

public record PatientRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        AddressRequest primaryAddress
) {
}
