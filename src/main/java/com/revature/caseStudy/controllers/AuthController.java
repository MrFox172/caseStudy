package com.revature.caseStudy.controllers;

import com.revature.caseStudy.dtos.LoginDTO;
import com.revature.caseStudy.dtos.RegisterDTO;
import com.revature.caseStudy.dtos.UserResponseDTO;
import com.revature.caseStudy.exceptions.InvalidUserException;
import com.revature.caseStudy.exceptions.RegistrationFailedException;
import com.revature.caseStudy.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){this.authService=authService;}

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpSession session)
    {
        try {
            UserResponseDTO user = authService.findByCredentials(loginDTO);
            session.setAttribute("user",user);
            return ResponseEntity.ok(user);
        }
        catch(InvalidUserException e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterDTO registerDTO, HttpSession session)
    {
        try {
            UserResponseDTO user = authService.registerWithCredentials(registerDTO);
            session.setAttribute("user",user);
            return ResponseEntity.ok(user);
        }
        catch(RegistrationFailedException e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session)
    {
        if(session.getAttribute("user")==null)
            return ResponseEntity.ok().body("You are already signed out.");
        else
        {
            session.removeAttribute("user");
            return ResponseEntity.ok().body("You have been signed out.");
        }
    }



}
