package com.api.eccom.service;


import com.api.eccom.model.Products;
import com.api.eccom.repository.ProductsRepository;

import com.api.eccom.validation.ProductValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductValidation productValidation;

    @Autowired
    private ProductsRepository productRepository;

    public List<Products> create(List<Products> productList) throws Exception {
        List<Products> productListed = new ArrayList<>();
        for(Products products : productList) {
            productListed.add((createProduct(products)));
        }
        return productListed;
    }

    private Products createProduct(Products product) throws Exception {
        productValidation.createValidation(product);
        return productRepository.save(product);
    }

    public Products update(Products newProduct) throws Exception{
        productValidation.updateValidation(newProduct);
        return productRepository.save(newProduct);
    }

    public Products findById(Long id) throws Exception{
        productValidation.findByIdValidation(id);
        return productRepository.findById(id).orElse(null);
    }

    public List<Products> findAll() {
        return this.productRepository.findAll();
    }

    public Products deleteById(Long id) throws Exception {
        productValidation.deleteValidation(id);
        productRepository.deleteById(id);
        return null;
    }
}
