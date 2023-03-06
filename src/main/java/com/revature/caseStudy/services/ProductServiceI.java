package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.ProductDTO;
import com.revature.caseStudy.dtos.ProductDetailDTO;
import com.revature.caseStudy.exceptions.ProductRetrievalFailedException;

import java.util.List;

public interface ProductServiceI {
    public List<ProductDTO> getAllProducts() throws ProductRetrievalFailedException;
    public ProductDetailDTO getProductById(int product) throws ProductRetrievalFailedException;

}
