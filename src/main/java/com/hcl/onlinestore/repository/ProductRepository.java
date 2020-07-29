package com.hcl.onlinestore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.onlinestore.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Optional<List<Product>> findByProductNameContains(String productName);

}
