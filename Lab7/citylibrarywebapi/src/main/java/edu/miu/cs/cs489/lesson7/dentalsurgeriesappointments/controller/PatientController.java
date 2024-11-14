package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.controller;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/adsweb/api/v1/patient")
public class PatientController {

    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<PatientResponse>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Integer id) throws PatientNotFoundException {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<PatientResponse> registerNewPatient(@Valid @RequestBody PatientRequest patientRequest) {
        return new ResponseEntity<>(patientService.addNewPatient(patientRequest), HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Integer id,
                                                     @RequestBody PatientRequest editedPatient) {
        return new ResponseEntity<>(patientService.updatePatient(id, editedPatient),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        patientService.deletePatientById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/search/{searchString}")
    public ResponseEntity<List<PatientResponse>> searchPatient(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.searchPatient(searchString));
    }

}
