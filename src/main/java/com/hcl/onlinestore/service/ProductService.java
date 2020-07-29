package com.hcl.onlinestore.service;

import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;

public interface ProductService {

	public ProductResponseDto getProductsByProuctName(Integer userId, String productName) throws ProductNotFoundException, UserNotFoundException;

}
