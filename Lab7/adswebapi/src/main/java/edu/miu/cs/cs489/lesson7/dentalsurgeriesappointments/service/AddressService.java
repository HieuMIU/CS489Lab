package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address.AddressResponse2;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model.Address;

import java.util.List;

public interface AddressService {

    Address addNewAddress(Address newAddress);
    List<AddressResponse2> getAllAddresses();

    void deleteAddressById(Integer addressId);

}
