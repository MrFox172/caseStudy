package com.revature.caseStudy.controllers;

import com.revature.caseStudy.dtos.ProductDTO;
import com.revature.caseStudy.dtos.ProductDetailDTO;
import com.revature.caseStudy.exceptions.ProductRetrievalFailedException;
import com.revature.caseStudy.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService)
    {
        this.productService=productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts()
    {
        try{
            return ResponseEntity.ok().body(productService.getAllProducts());
        }
        catch(ProductRetrievalFailedException e)
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

    @GetMapping("/details/{productId}")
    public ResponseEntity<ProductDetailDTO> getProduct(@PathVariable int productId)
    {
        try{
            return ResponseEntity.ok().body(productService.getProductById(productId));
        }
        catch(ProductRetrievalFailedException e)
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

}
