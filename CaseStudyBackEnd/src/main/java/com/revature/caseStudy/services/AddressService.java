package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.CheckOutDTO;
import com.revature.caseStudy.dtos.RegisterDTO;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.models.Address;
import com.revature.caseStudy.repositories.AddressRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements AddressServiceI {

    private AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository)
    {
        this.addressRepository = addressRepository;
    }


    @Override
    public Address getAddressFromRegisterDTO(RegisterDTO registerDTO) throws InvalidAddressException {
        String street = registerDTO.getStreet();
        int zipcode = registerDTO.getZipCode();
        String country = registerDTO.getCountry();

        if(street.equals("")||zipcode<=9999||country.equals(""))
            throw new InvalidAddressException();

        Optional<Address> addressFound = addressRepository.findByMatchAll(street, registerDTO.getCity(), registerDTO.getState(),zipcode,country);
        Address address;
        if(addressFound.isEmpty())
        {
            address = new Address(0,registerDTO.getStreet(), registerDTO.getCity(), registerDTO.getState(), registerDTO.getZipCode(), registerDTO.getCountry());
            address = addressRepository.save(address);
        }
        else
            address = addressFound.get();
        return address;

    }

    @Override
    public Address getAddressFromCheckOutDTO(CheckOutDTO checkOutDTO) throws InvalidAddressException {
        String street = checkOutDTO.getStreet();
        int zipcode = checkOutDTO.getZipCode();
        String country = checkOutDTO.getCountry();

        if(street.equals("")||zipcode>9999||country.equals(""))
            throw new InvalidAddressException();

        Optional<Address> addressFound = addressRepository.findByMatchAll(street, checkOutDTO.getCity(), checkOutDTO.getState(),zipcode,country);
        Address address = addressFound.get();
        if(address==null)
        {
            address = new Address(0,checkOutDTO.getStreet(), checkOutDTO.getCity(), checkOutDTO.getState(), checkOutDTO.getZipCode(), checkOutDTO.getCountry());
            address = addressRepository.save(address);
        }
        return address;
    }
}
