package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.repository;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findByFirstNameContainingOrLastNameContainingOrPhoneNumberContainingOrEmailContainingOrOrPrimaryAddress_StreetContainingOrPrimaryAddress_CityContainingOrPrimaryAddress_StateContaining(String firstName, String lastName, String phoneNumber, String email, String street, String city, String state);
}
