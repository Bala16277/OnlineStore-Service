package com.hcl.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.onlinestore.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
