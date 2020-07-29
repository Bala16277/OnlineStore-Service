package com.hcl.onlinestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.onlinestore.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
