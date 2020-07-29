package com.hcl.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.onlinestore.entity.ProductOrder;


public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{

}
