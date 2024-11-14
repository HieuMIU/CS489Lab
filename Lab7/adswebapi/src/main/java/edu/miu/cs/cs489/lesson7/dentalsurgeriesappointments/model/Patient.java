package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "First Name is required and cannot be null or empty string or blank spaces")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last Name is required and cannot be null or empty string or blank spaces")
    private String lastName;

    private String phoneNumber;

    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true, nullable = true)
    @JsonManagedReference
    private Address primaryAddress;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", primaryAddress=" + primaryAddress +
                '}';
    }
}
