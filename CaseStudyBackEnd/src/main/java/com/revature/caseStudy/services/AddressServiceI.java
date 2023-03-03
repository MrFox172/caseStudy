package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.CheckOutDTO;
import com.revature.caseStudy.dtos.RegisterDTO;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.models.Address;

public interface AddressServiceI {
    public Address getAddressFromRegisterDTO(RegisterDTO registerDTO) throws InvalidAddressException;
    public Address getAddressFromCheckOutDTO(CheckOutDTO checkOutDTO) throws InvalidAddressException;
}
