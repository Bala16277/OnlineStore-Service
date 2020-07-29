package com.hcl.onlinestore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.onlinestore.dto.OrderHistoryResDto;
import com.hcl.onlinestore.dto.OrderResDto;
import com.hcl.onlinestore.dto.ProductOrderDto;
import com.hcl.onlinestore.entity.OrderHistory;
import com.hcl.onlinestore.entity.Product;
import com.hcl.onlinestore.entity.ProductOrder;
import com.hcl.onlinestore.entity.User;
import com.hcl.onlinestore.repository.OrderHistoryRepository;
import com.hcl.onlinestore.repository.ProductOrderRepository;
import com.hcl.onlinestore.repository.ProductRepository;
import com.hcl.onlinestore.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService{

	private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderHistoryRepository orderHistoryRepository;
	
	@Autowired
	ProductOrderRepository productOrderRepository;
	
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
		
		User user = userRepository.findByUserId(userId);
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
	}
	
	private void saveProductOrder(Product product,int quantity, OrderHistory orderHistory) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setProduct(product);
		productOrder.setQuantity(quantity);
		productOrder.setOrderHistory(orderHistory);
		
		productOrderRepository.save(productOrder); 
	}

	public List<OrderHistoryResDto> getOrderHistory(int userId) {
	
		List<OrderHistoryResDto> orderHistoryResDtos = new ArrayList();
		
		 User user = userRepository.findByUserId(userId);
		
			List<OrderHistory> orderHistorys = orderHistoryRepository.findByUser(user);
			
			for(OrderHistory orderHistory: orderHistorys) {
				OrderHistoryResDto OrderHistoryResDto = new OrderHistoryResDto();
				OrderHistoryResDto.setOrderHistoryId(orderHistory.getOrderHistoryId());
				OrderHistoryResDto.setTotalCost(orderHistory.getTotalCost());
				
				orderHistoryResDtos.add(OrderHistoryResDto);
			}
		
		return orderHistoryResDtos;
	}
}
