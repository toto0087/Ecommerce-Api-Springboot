package com.api.eccom.validation;

import com.api.eccom.model.Products;
import com.api.eccom.repository.ProductsRepository;

import java.util.Optional;

public class ProductValidation {
    private final ProductsRepository productRepository;

    public ProductValidation(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createValidation(Products product) throws Exception {
        Optional<Products> optionalProduct = productRepository.findByCode(product.getCode());

        hasInvalidValues(product);

        if(optionalProduct.isPresent()){
            throw new Exception("El código ya está en uso");
        }
    }


    public void updateValidation(Products product) throws Exception {
        Optional<Products> optionalProduct = productRepository.findById(product.getId());

        hasInvalidValues(product);

        if (product.getId() <= 0){
            throw new Exception("El id no es válido");
        }
        if(optionalProduct.isEmpty()){
            throw new Exception("El producto que intenta modificar no existe");
        }

        Optional<Products> optionalProductWithCode = productRepository.findByCode(product.getCode());

        if (optionalProductWithCode.isPresent() && !optionalProductWithCode.get().getId().equals(product.getId())) {
            throw new Exception("El código ya está en uso");
        }

    }


    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public void deleteValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }


    private void hasInvalidValues(Products product) throws Exception {
        if (product.getCode() == null || product.getCode().isEmpty()) {
            throw new Exception("El nombre del cliente no puede estar vacío.");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new Exception("El apellido del cliente no puede estar vacío.");
        }
        if (product.getStock() <= 0) {
            throw new Exception("El stock es inválido.");
        }
        if(product.getPrice() <= 0){
            throw new Exception("El precio es inválido.");
        }
    }
}
