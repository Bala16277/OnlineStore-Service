package com.hcl.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.service.ProductService;
import com.hcl.onlinestore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/{userId}/products")
	public ResponseEntity<ProductResponseDto> getProductsByProductName(@PathVariable Integer userId, @RequestParam String productName) throws ProductNotFoundException{
		ProductResponseDto productResponseDto;
		productResponseDto = productService.getProductsByProuctName(productName);
		return new ResponseEntity<>(productResponseDto,HttpStatus.OK);
	}

}
