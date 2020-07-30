package com.hcl.onlinestore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.onlinestore.entity.Product;
import com.hcl.onlinestore.entity.Review;
import com.hcl.onlinestore.entity.User;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public Optional<Review> findByProductAndUser(Product product, User user);	

}
