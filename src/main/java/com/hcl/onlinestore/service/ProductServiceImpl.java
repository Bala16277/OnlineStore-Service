package com.hcl.onlinestore.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.onlinestore.config.ApplicationConstants;
import com.hcl.onlinestore.dto.ProductDto;
import com.hcl.onlinestore.dto.ProductResponseDto;
import com.hcl.onlinestore.entity.Product;
import com.hcl.onlinestore.entity.User;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;
import com.hcl.onlinestore.repository.ProductRepository;
import com.hcl.onlinestore.repository.UserRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserRepository userRepository;

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

}
