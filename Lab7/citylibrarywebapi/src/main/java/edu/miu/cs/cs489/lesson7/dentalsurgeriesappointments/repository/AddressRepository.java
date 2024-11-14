package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.repository;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
