package com.hcl.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.onlinestore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUserId(int userId);

}
