package com.api.eccom.service;

import com.api.eccom.model.Products;
import com.api.eccom.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productRepository;


}
