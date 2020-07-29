package com.hcl.onlinestore.service;

import java.util.List;

import com.hcl.onlinestore.dto.OrderResDto;
import com.hcl.onlinestore.dto.ProductOrderDto;
import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;

public interface ProductService {

	public ProductResponseDto getProductsByProuctName(Integer userId, String productName)
			throws ProductNotFoundException, UserNotFoundException;

	public OrderResDto placeOrder(int userId, List<ProductOrderDto> productOrderDto);

}
