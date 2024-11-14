package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.advice;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DentalSurgeriesAppointmentsWebAPIExceptionHandler {


    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePublisherNotFoundException(PatientNotFoundException publisherNotFoundException) {
        Map<String , String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("errorMessage", publisherNotFoundException.getMessage());
        return errorMessageMap;
    }

}
