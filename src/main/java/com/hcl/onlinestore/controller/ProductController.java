package com.hcl.onlinestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.onlinestore.dto.OrderHistoryResDto;
import com.hcl.onlinestore.dto.OrderResDto;
import com.hcl.onlinestore.dto.ProductOrderDto;
import com.hcl.onlinestore.service.ProductServiceImpl;


@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@PostMapping("/{userId}")
	public ResponseEntity<OrderResDto> buyProducts(@PathVariable int userId, @RequestBody List<ProductOrderDto> products){
		OrderResDto transactions = productServiceImpl.placeOrder(userId, products);
		
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
	
	@GetMapping("/orderhistory")
	public ResponseEntity<List<OrderHistoryResDto>> getOrderHistory(@RequestParam int userId){
	List<OrderHistoryResDto> orderHistory = productServiceImpl.getOrderHistory(userId);
		
		return new ResponseEntity<>(orderHistory, HttpStatus.OK);
	}
}
