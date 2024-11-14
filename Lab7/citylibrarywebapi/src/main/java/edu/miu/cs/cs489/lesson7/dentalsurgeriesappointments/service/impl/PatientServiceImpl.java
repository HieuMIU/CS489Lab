package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service.impl;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address.AddressResponse;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model.Address;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model.Patient;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.repository.AddressRepository;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.repository.PatientRepository;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service.PatientService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private AddressRepository addressRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
                              AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll(Sort.by("lastName"))
                .stream()
                .map(p -> new PatientResponse(
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getPhoneNumber(),
                        p.getEmail(),
                        (p.getPrimaryAddress()!=null)?new AddressResponse(
                                p.getPrimaryAddress().getAddressId(),
                                p.getPrimaryAddress().getStreet(),
                                p.getPrimaryAddress().getCity(),
                                p.getPrimaryAddress().getState(),
                                p.getPrimaryAddress().getZipCode()
                        ):null
                )).toList();
    }

    @Override
    public PatientResponse addNewPatient(PatientRequest patientRequest) {
        var newPatient = new Patient(null,
                patientRequest.firstName(),
                patientRequest.lastName(),
                patientRequest.phoneNumber(),
                patientRequest.email(),
                new Address(null,
                        patientRequest.primaryAddress().street(),
                        patientRequest.primaryAddress().city(),
                        patientRequest.primaryAddress().state(),
                        patientRequest.primaryAddress().zipCode()));
        var savedPatient =  patientRepository.save(newPatient);
        return new PatientResponse(
                savedPatient.getId(),
                savedPatient.getFirstName(),
                savedPatient.getLastName(),
                savedPatient.getPhoneNumber(),
                savedPatient.getEmail(),
                new AddressResponse(
                        savedPatient.getPrimaryAddress().getAddressId(),
                        savedPatient.getPrimaryAddress().getStreet(),
                        savedPatient.getPrimaryAddress().getCity(),
                        savedPatient.getPrimaryAddress().getState(),
                        savedPatient.getPrimaryAddress().getZipCode()
                )
        );
    }

    @Override
    public PatientResponse getPatientById(Integer patientId) throws PatientNotFoundException {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(String.format("Error: Patient with Id, %d, is not found",
                        patientId)));

        return new PatientResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                (patient.getPrimaryAddress()!=null)?new AddressResponse(
                        patient.getPrimaryAddress().getAddressId(),
                        patient.getPrimaryAddress().getStreet(),
                        patient.getPrimaryAddress().getCity(),
                        patient.getPrimaryAddress().getState(),
                        patient.getPrimaryAddress().getZipCode()
                ):null);
    }

    @Override
    public PatientResponse updatePatient(Integer patientId, PatientRequest editedPatient) {

        var patient = patientRepository.findById(patientId).orElse(null);

        if(patient != null ) {
            patient.setFirstName(editedPatient.firstName());
            patient.setLastName(editedPatient.lastName());
            patient.setPhoneNumber(editedPatient.phoneNumber());
            patient.setEmail(editedPatient.email());

            if(patient.getPrimaryAddress()!=null) {
                var address = patient.getPrimaryAddress();
                address.setStreet(editedPatient.primaryAddress().street());
                address.setCity(editedPatient.primaryAddress().city());
                address.setState(editedPatient.primaryAddress().state());
                address.setZipCode(editedPatient.primaryAddress().zipCode());
            } else {
                var newAddress = new Address();
                newAddress.setStreet(editedPatient.primaryAddress().street());
                newAddress.setCity(editedPatient.primaryAddress().city());
                newAddress.setState(editedPatient.primaryAddress().state());
                newAddress.setZipCode(editedPatient.primaryAddress().zipCode());
                newAddress.setPatient(patient);
                patient.setPrimaryAddress(newAddress);
            }
            var updatedPatient = patientRepository.save(patient);

            return new PatientResponse(
                    updatedPatient.getId(),
                    updatedPatient.getFirstName(),
                    updatedPatient.getLastName(),
                    updatedPatient.getPhoneNumber(),
                    updatedPatient.getEmail(),
                    (updatedPatient.getPrimaryAddress()!=null)?new AddressResponse(
                            updatedPatient.getPrimaryAddress().getAddressId(),
                            updatedPatient.getPrimaryAddress().getStreet(),
                            updatedPatient.getPrimaryAddress().getCity(),
                            updatedPatient.getPrimaryAddress().getState(),
                            updatedPatient.getPrimaryAddress().getZipCode()
                    ):null);
        } else {
            return null;
        }
    }

    @Override
    public void deletePatientById(Integer patientId) {
        patientRepository.deleteById(patientId);
    }

    @Override
    public List<PatientResponse> searchPatient(String searchString) {
        return patientRepository.findByFirstNameContainingOrLastNameContainingOrPhoneNumberContainingOrEmailContainingOrOrPrimaryAddress_StreetContainingOrPrimaryAddress_CityContainingOrPrimaryAddress_StateContaining(searchString,searchString,searchString,searchString,searchString,searchString,searchString)
                .stream()
                .map(p -> new PatientResponse(
                        p.getId(),
                        p.getFirstName(),
                        p.getLastName(),
                        p.getPhoneNumber(),
                        p.getEmail(),
                        (p.getPrimaryAddress()!=null)?new AddressResponse(
                                p.getPrimaryAddress().getAddressId(),
                                p.getPrimaryAddress().getStreet(),
                                p.getPrimaryAddress().getCity(),
                                p.getPrimaryAddress().getState(),
                                p.getPrimaryAddress().getZipCode()
                        ):null
                )).toList();
    }
}
