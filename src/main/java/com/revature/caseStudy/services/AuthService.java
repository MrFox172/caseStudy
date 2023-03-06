package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.LoginDTO;
import com.revature.caseStudy.dtos.RegisterDTO;
import com.revature.caseStudy.dtos.UserResponseDTO;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.exceptions.InvalidUserException;
import com.revature.caseStudy.exceptions.RegistrationFailedException;
import com.revature.caseStudy.models.Address;
import com.revature.caseStudy.models.User;
import com.revature.caseStudy.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@NoArgsConstructor
public class AuthService implements AuthServiceI{

    private UserRepository userRepository;
    private AddressService addressService;

    @Autowired
    public AuthService(UserRepository userRepository, AddressService addressService)
    {this.userRepository=userRepository;this.addressService = addressService;}

    @Override
    public UserResponseDTO findByCredentials(LoginDTO loginDTO) throws InvalidUserException {

        Optional<User> potentialUser = userRepository.findByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if(potentialUser.get()==null)
            throw new InvalidUserException();
        User user = potentialUser.get();
        UserResponseDTO userDTO = new UserResponseDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPrimaryAddress().getCountry());
        return userDTO;
    }

    @Override
    @Transactional
    public UserResponseDTO registerWithCredentials(RegisterDTO registerDTO) throws RegistrationFailedException, InvalidAddressException {
        String firstName = registerDTO.getFirstName().strip();
        String lastName = registerDTO.getLastName().strip();
        String email = registerDTO.getEmail().strip();
        String password = registerDTO.getPassword().strip();

        if(firstName.equals("")||lastName.equals(""))
            throw new RegistrationFailedException();
        if(!email.contains("@")||!email.contains(".com")||email.indexOf("@")==0||email.indexOf("@")!=email.lastIndexOf("@"))
            throw new RegistrationFailedException();
        if(password.length()<7) //this check can be stronger, speeding past this for time's sake.
            throw new RegistrationFailedException();

        Address primaryAddress = addressService.getAddressFromRegisterDTO(registerDTO);

        Optional<User> userFound = userRepository.findByEmail(email);
        if(userFound.isPresent())
            throw new RegistrationFailedException();

        User user = new User(0,email,password,firstName,lastName,primaryAddress,null);
        user = userRepository.save(user);

        UserResponseDTO userDTO = new UserResponseDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPrimaryAddress().getCountry());
        return userDTO;
    }

    @Override
    public User findById(int id) throws InvalidUserException
    {
        Optional<User> potentialUser = userRepository.findById(id);
        if(potentialUser.isEmpty())
            throw new InvalidUserException();
        return potentialUser.get();
    }
}
