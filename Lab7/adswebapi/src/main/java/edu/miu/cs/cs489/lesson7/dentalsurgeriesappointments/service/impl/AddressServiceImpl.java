package edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service.impl;

import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.address.AddressResponse2;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.dto.patient.PatientResponse2;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.model.Address;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.repository.AddressRepository;
import edu.miu.cs.cs489.lesson7.dentalsurgeriesappointments.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address addNewAddress(Address newAddress) {
        return addressRepository.save(newAddress);
    }

    @Override
    public List<AddressResponse2> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(a -> new AddressResponse2(
                        a.getAddressId(),
                        a.getStreet(),
                        a.getCity(),
                        a.getState(),
                        a.getZipCode(),
                        (a.getPatient()!= null)?new PatientResponse2(
                                a.getPatient().getId(),
                                a.getPatient().getFirstName(),
                                a.getPatient().getLastName(),
                                a.getPatient().getPhoneNumber(),
                                a.getPatient().getEmail()
                        ): null
                )).toList();
    }

    @Override
    public void deleteAddressById(Integer addressId) {
        addressRepository.deleteById(addressId);
    }


}
