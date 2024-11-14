package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.exception.PatientNotFoundException;

import java.util.List;

public interface PatientService {

    List<PatientResponse> getAllPatients();

    PatientResponse addNewPatient(PatientRequest PatientRequest);

    PatientResponse getPatientById(Integer PatientId) throws PatientNotFoundException;

    PatientResponse updatePatient(Integer PatientId, PatientRequest editedPatient);

    void deletePatientById(Integer PatientId);

    List<PatientResponse> searchPatient(String searchString);

}
