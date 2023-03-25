package com.api.eccom.controller;

import com.api.eccom.exception.ProductAlreadyExistException;
//import com.api.eccom.exception.ProductNotFoundException;
import com.api.eccom.model.Products;
import com.api.eccom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(path = "/")
    public ResponseEntity<Products> create(@RequestBody Products product) throws ProductAlreadyExistException {
        return new ResponseEntity<>(this.productService.create(product), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Products> update(@RequestBody Products product, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.productService.update(product,id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Products> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.productService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Products>> findAll()  {
        return new ResponseEntity<>(this.productService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Products> deleteById(@PathVariable Long id) throws Exception {
       return new ResponseEntity<>(this.productService.deleteById(id), HttpStatus.OK);
    }

}