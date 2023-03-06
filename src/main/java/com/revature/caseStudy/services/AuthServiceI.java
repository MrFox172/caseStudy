package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.LoginDTO;
import com.revature.caseStudy.dtos.RegisterDTO;
import com.revature.caseStudy.dtos.UserResponseDTO;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.exceptions.InvalidUserException;
import com.revature.caseStudy.exceptions.RegistrationFailedException;
import com.revature.caseStudy.models.User;

public interface AuthServiceI {
    public UserResponseDTO findByCredentials(LoginDTO loginDTO) throws InvalidUserException;
    public UserResponseDTO registerWithCredentials(RegisterDTO registerDTO) throws RegistrationFailedException, InvalidAddressException;

    User findById(int id) throws InvalidUserException;
}
