package com.revature.caseStudy.controllers;

import com.revature.caseStudy.dtos.CheckOutDTO;
import com.revature.caseStudy.exceptions.CheckoutFailedException;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.exceptions.InvalidUserException;
import com.revature.caseStudy.exceptions.ProductRetrievalFailedException;
import com.revature.caseStudy.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class CheckoutController {
    private CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService)
    {
        this.checkoutService=checkoutService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> processCheckout(@RequestBody CheckOutDTO checkOutDTO)
    {
        try
        {
            checkoutService.processCheckout(checkOutDTO);
            return (ResponseEntity<String>) ResponseEntity.accepted().body("Your order has been taken.");
        }
        catch(CheckoutFailedException e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().body("There was an error handling your credit card");
        }
        catch(InvalidAddressException e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().body("There was an error in assigning the shipping addresss.");
        }
        catch(InvalidUserException e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().body("There was an error in user details.");
        }
        catch(ProductRetrievalFailedException e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().body("A product in your cart no longer available.");
        }
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
            return ResponseEntity.badRequest().body("An internal exception occurred.");
        }
    }
}
