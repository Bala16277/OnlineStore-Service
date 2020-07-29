package com.hcl.onlinestore.service;

import java.util.List;

import com.hcl.onlinestore.dto.OrderResDto;
import com.hcl.onlinestore.dto.ProductOrderDto;

public interface ProductService {

	public OrderResDto placeOrder(int userId, List<ProductOrderDto> productOrderDto);
}
