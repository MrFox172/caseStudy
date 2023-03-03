package com.revature.caseStudy.services;

import com.revature.caseStudy.dtos.ProductDTO;
import com.revature.caseStudy.dtos.ProductDetailDTO;
import com.revature.caseStudy.exceptions.ProductRetrievalFailedException;
import com.revature.caseStudy.models.Product;
import com.revature.caseStudy.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductServiceI{

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() throws ProductRetrievalFailedException {
        List<Product> products = productRepository.findAll();
        if(products.isEmpty())
            throw new ProductRetrievalFailedException();

        List<ProductDTO> productCards = new ArrayList<ProductDTO>();
        products.forEach((product)->{
            ProductDTO productDTO = new ProductDTO(product.getId(),product.getName(),product.getPrice(),product.getPictureUrl());
            productCards.add(productDTO);
        });

        return productCards;
    }

    public ProductDetailDTO getProductById(int product) throws ProductRetrievalFailedException{
        Optional<Product> potentialProduct = productRepository.findById(product);
        if(potentialProduct.isEmpty())
            throw new ProductRetrievalFailedException();

        Product realProduct = potentialProduct.get();
        ProductDetailDTO pdDTO = new ProductDetailDTO(realProduct.getId(), realProduct.getName(), realProduct.getDescriptionMain(), realProduct.getPrice(), realProduct.getPictureUrl(), realProduct.getDescriptionDetails());
        return pdDTO;
    }

    public Product getProductModelById(int product) throws ProductRetrievalFailedException{
        Optional<Product> potentialProduct = productRepository.findById(product);
        if(potentialProduct.isEmpty())
            throw new ProductRetrievalFailedException();
        Product realProduct = potentialProduct.get();
        return realProduct;
    }
}
