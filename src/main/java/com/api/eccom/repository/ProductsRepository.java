package com.api.eccom.repository;

import com.api.eccom.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    Optional<Products> findByCodigo(String codigo);
}
