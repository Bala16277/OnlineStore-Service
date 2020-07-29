package com.hcl.onlinestore.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.onlinestore.config.ApplicationConstants;
import com.hcl.onlinestore.dto.OrderHistoryResDto;
import com.hcl.onlinestore.dto.OrderResDto;
import com.hcl.onlinestore.dto.ProductDto;
import com.hcl.onlinestore.dto.ProductOrderDto;
import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.entity.OrderHistory;
import com.hcl.onlinestore.entity.Product;
import com.hcl.onlinestore.entity.ProductOrder;
import com.hcl.onlinestore.entity.User;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;
import com.hcl.onlinestore.repository.OrderHistoryRepository;
import com.hcl.onlinestore.repository.ProductOrderRepository;
import com.hcl.onlinestore.repository.ProductRepository;
import com.hcl.onlinestore.repository.UserRepository;

@Service

public class ProductServiceImpl implements ProductService {
	

	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	
	@Autowired
	ProductOrderRepository productOrderRepository;
	
	

	public ProductResponseDto getProductsByProuctName(Integer userId, String productName)
			throws ProductNotFoundException, UserNotFoundException {
		ProductResponseDto productResponseDto = new ProductResponseDto();
		List<ProductDto> productDtos = new ArrayList<>();
		List<Product> productList = new ArrayList<>();
		Optional<List<Product>> products = productRepository.findByProductNameContains(productName);
		Optional<User> users = userRepository.findByUserId(userId);
		if (users.isPresent()) {
			if (products.isPresent()) {
				productList = products.get();
				for (Product product : productList) {
					ProductDto productDto = new ProductDto();
					BeanUtils.copyProperties(product, productDto);
					productDtos.add(productDto);
				}
				List<ProductDto> productDtos1 = productDtos.stream()
						.sorted(Comparator.comparing(ProductDto::getProductRating).reversed())
						.collect(Collectors.toList());

				productResponseDto.setStatusMessage(ApplicationConstants.PRODUCT_LIST);
				productResponseDto.setStatusCode(HttpStatus.OK.value());
				productResponseDto.setProductDto(productDtos1);
				return productResponseDto;
			} else {
				throw new ProductNotFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
			}
		} else {
			throw new UserNotFoundException(ApplicationConstants.USER_NOT_FOUND);
		}
	}


	
	
	
	
	
	public OrderResDto placeOrder(int userId, List<ProductOrderDto> productList) {
		double totalCost = 0.00;
		OrderResDto orderResDto = new OrderResDto();
		for(ProductOrderDto product : productList) {
			Optional<Product> products = productRepository.findByProductId(product.getProductId());
			if(products.isPresent()) {
				Product productDetails = products.get();
				totalCost = totalCost + (productDetails.getProductPrice()* product.getQuantity());
				
			}
			logger.info("Total cost:  " + totalCost);
			
		}
		
		Optional<User> users = userRepository.findByUserId(userId);
		if(users.isPresent()) {
			
		
		User user = users.get();
		logger.info("User is:  " + user.getUserName());
		
		OrderHistory orderHistory = new OrderHistory();
		orderHistory.setTotalCost(totalCost);
		orderHistory.setUser(user);
		orderHistoryRepository.save(orderHistory);
		
		OrderHistory orderHistory1 = orderHistoryRepository.findTopByOrderByOrderHistoryIdDesc();
		
		for(ProductOrderDto product : productList) {
			Optional<Product> products = productRepository.findByProductId(product.getProductId());
			if(products.isPresent()) {
				Product productDetails = products.get();
				saveProductOrder(productDetails, product.getQuantity(),orderHistory1);
				
			}
		}
		logger.info("orderHistory id is:  " + orderHistory1.getOrderHistoryId());
		
		orderResDto.setMessage("Order placed successfully");
		orderResDto.setStatusCode(HttpStatus.CREATED.value());
		return orderResDto;
		} else {
			orderResDto.setMessage("user not found");
			orderResDto.setStatusCode(HttpStatus.CREATED.value());
			return orderResDto;
		}
	}
	
	private void saveProductOrder(Product product,int quantity, OrderHistory orderHistory) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setOrderHistory(orderHistory);
		
		productOrderRepository.save(productOrder); 
	}

	public List<OrderHistoryResDto> getOrderHistory(int userId) {
	
		List<OrderHistoryResDto> orderHistoryResDtos = new ArrayList<>();
		
		 Optional<User> users = userRepository.findByUserId(userId);
		 if(users.isPresent()) {
			 User user = users.get();
			List<OrderHistory> orderHistorys = orderHistoryRepository.findByUser(user);
			
			for(OrderHistory orderHistory: orderHistorys) {
				OrderHistoryResDto OrderHistoryResDto = new OrderHistoryResDto();
				OrderHistoryResDto.setOrderHistoryId(orderHistory.getOrderHistoryId());
				OrderHistoryResDto.setTotalCost(orderHistory.getTotalCost());
				
				orderHistoryResDtos.add(OrderHistoryResDto);
			}
		
		return orderHistoryResDtos;
	} else {
		return null;
	}
	}
}
