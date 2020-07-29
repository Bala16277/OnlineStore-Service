package com.hcl.onlinestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.onlinestore.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByProductId(Integer productId);

}
