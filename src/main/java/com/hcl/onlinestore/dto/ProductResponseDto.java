package com.hcl.onlinestore.dto;

import java.util.List;

public class ProductResponseDto {
	
	private String statusMessage;
	
	private Integer statusCode;
	
	private List<ProductDto> productDto;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public List<ProductDto> getProductDto() {
		return productDto;
	}

	public void setProductDto(List<ProductDto> productDto) {
		this.productDto = productDto;
	}

}
