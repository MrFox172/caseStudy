package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.CheckOutDTO;
import com.revature.caseStudy.exceptions.CheckoutFailedException;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.exceptions.InvalidUserException;
import com.revature.caseStudy.exceptions.ProductRetrievalFailedException;

public interface CheckoutServiceI {
    public void processCheckout(CheckOutDTO checkOutDTO) throws InvalidAddressException, CheckoutFailedException, InvalidUserException, ProductRetrievalFailedException;
}
