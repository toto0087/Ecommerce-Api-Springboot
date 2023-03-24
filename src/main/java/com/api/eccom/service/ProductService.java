package com.api.eccom.service;

import com.api.eccom.exception.ProductAlreadyExistException;
import com.api.eccom.exception.ProductNotFoundException;
import com.api.eccom.model.Products;
import com.api.eccom.repository.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductsRepository productRepository;

    public Products create(Products newProduct) throws ProductAlreadyExistException {
        Optional<Products> productOp = this.productRepository.findByCodigo(newProduct.getCode());

        if(productOp.isPresent()) {
            log.info("El producto ya existe" + newProduct);
            throw new ProductAlreadyExistException("El producto que quiere agregar ya existe");
        } else {
            return this.productRepository.save(newProduct);
        }
    }

    public Products update(Products newProduct,Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }
        Optional<Products> productOp = this.productRepository.findById(id);

        if(productOp.isEmpty()) {
            log.info("El producto que intenta modificar no existe" + newProduct);
            throw new ProductNotFoundException("El producto que intenta modificar no existe");
        } else {
            Products productBd = productOp.get();

            productBd.setCode(newProduct.getCode());
            productBd.setStock(newProduct.getStock());
            productBd.setPrice(newProduct.getPrice());
            productBd.setDescription(newProduct.getDescription());

            return this.productRepository.save(productBd);
        }
    }

    public Products findById(Long id) throws Exception{
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Products> productOp = this.productRepository.findById(id);

        if(productOp.isEmpty()) {
            log.info("El producto con el id brindado no existe en la base de datos" + productOp);
            throw new ProductNotFoundException("El producto solicitado no existe");
        } else {
            return productOp.get();
        }
    }

    public List<Products> findAll() {
        return this.productRepository.findAll();
    }

        public Products deleteById(Long id) throws Exception {
        if (id <= 0) {
            throw new Exception("El id no es valido");
        }

        Optional<Products> productOp = this.productRepository.findById(id);

        if(productOp.isEmpty()) {
            log.info("El producto con el id brindado no existe en la base de datos" + productOp);
            throw new ProductNotFoundException("El producto solicitado no existe");
        } else {
            return this.productRepository.deleteById(Long id);
        }
    }
}
