package com.hcl.onlinestore.controller;

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

import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.dto.ReviewRequestDto;
import com.hcl.onlinestore.dto.ReviewResponseDto;
import com.hcl.onlinestore.exception.InvalidOrderException;
import com.hcl.onlinestore.exception.InvalidReviewerException;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;
import com.hcl.onlinestore.service.ProductService;
import com.hcl.onlinestore.service.ReviewService;
import com.hcl.onlinestore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/{userId}/products")
	public ResponseEntity<ProductResponseDto> getProductsByProductName(@PathVariable Integer userId, @RequestParam String productName) throws ProductNotFoundException, UserNotFoundException {
		ProductResponseDto productResponseDto;
		productResponseDto = productService.getProductsByProuctName(userId,productName);
		return new ResponseEntity<>(productResponseDto,HttpStatus.OK);
	}
	
	@PostMapping("/{userId}/reviews")
	public ResponseEntity<ReviewResponseDto> giveRatings(@PathVariable Integer userId, @RequestBody ReviewRequestDto reviewRequestDto) throws UserNotFoundException, InvalidReviewerException, InvalidOrderException, ProductNotFoundException {
		ReviewResponseDto reviewResponseDto;
		reviewResponseDto = reviewService.giveRatings(userId, reviewRequestDto);
		return new ResponseEntity<>(reviewResponseDto, HttpStatus.OK);
	}
}
