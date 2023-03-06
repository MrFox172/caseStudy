package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.CheckOutDTO;
import com.revature.caseStudy.exceptions.CheckoutFailedException;
import com.revature.caseStudy.exceptions.InvalidAddressException;
import com.revature.caseStudy.exceptions.InvalidUserException;
import com.revature.caseStudy.exceptions.ProductRetrievalFailedException;
import com.revature.caseStudy.models.Address;
import com.revature.caseStudy.models.Order;
import com.revature.caseStudy.models.Product;
import com.revature.caseStudy.models.User;
import com.revature.caseStudy.repositories.OrderRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutService implements CheckoutServiceI{

    private AddressService addressService;
    private AuthService authService;
    private ProductService productService;
    private OrderRepository orderRepository;

    public CheckoutService(AddressService addressService, AuthService authService, ProductService productService, OrderRepository orderRepository) {
        this.addressService = addressService;
        this.authService = authService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void processCheckout(CheckOutDTO checkOutDTO) throws InvalidAddressException, CheckoutFailedException, InvalidUserException, ProductRetrievalFailedException {
        if(checkOutDTO.getProductIds().isEmpty())
            throw new CheckoutFailedException();
        if(checkOutDTO.getCreditCardNumber()<999999999999999L || checkOutDTO.getCreditCardNumber()>9999999999999999L)
            throw new CheckoutFailedException();

        Address address = addressService.getAddressFromCheckOutDTO(checkOutDTO);
        User user = authService.findById(checkOutDTO.getUserId());

        List<Product> productsList = new ArrayList<>();
        Integer[] productIds = checkOutDTO.getProductIds().toArray(new Integer[0]);
        double totalAmountOwed = 0;
        for(int i = 0; i<productIds.length; i++)
        {
            Product currentProduct = productService.getProductModelById(productIds[i]);
            productsList.add(currentProduct);
            totalAmountOwed += currentProduct.getPrice();
        }

        //TODO:Allow for other currencies in the future. State dependent taxes as well.
        totalAmountOwed += totalAmountOwed*.06; //6 cent sales tax.

        //TODO:Run a transaction on the card. For now, we assume infinite money :))))
        Order currentOrder = new Order(0, null, productsList, totalAmountOwed, address, user);
        orderRepository.save(currentOrder);
    }
}
