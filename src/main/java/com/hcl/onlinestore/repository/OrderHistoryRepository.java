package com.hcl.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.onlinestore.entity.OrderHistory;
import com.hcl.onlinestore.entity.User;


public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer>{

	OrderHistory findTopByOrderByOrderHistoryIdDesc();

	//List<OrderHistory> findByUserId(int userId);

	List<OrderHistory> findByUser(User user);

}
