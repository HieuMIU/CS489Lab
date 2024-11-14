package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DentalSurgeriesAppointmentsWebapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DentalSurgeriesAppointmentsWebapiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return (args) -> {
            System.out.println("Hello RESTful Web API");
            System.out.println("DentalSurgeriesAppointments WebAPI server. Starting...");
            System.out.println("DentalSurgeriesAppointments WebAPI server. Started.\nRunning Apache Tomcat service and Listening for HTTP Request on Port number, 8080");
        };
    }

}
