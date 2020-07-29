package com.hcl.onlinestore.service;

import java.util.List;

import com.hcl.onlinestore.dto.ProductDto;
import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.exception.ProductNotFoundException;

public interface ProductService {

	public ProductResponseDto getProductsByProuctName(String productName) throws ProductNotFoundException;

}
