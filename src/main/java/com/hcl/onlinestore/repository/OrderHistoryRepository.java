package com.hcl.onlinestore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.onlinestore.entity.OrderHistory;
import com.hcl.onlinestore.entity.User;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {

	OrderHistory findTopByOrderByOrderHistoryIdDesc();

	List<OrderHistory> findByUser(User user);

}
