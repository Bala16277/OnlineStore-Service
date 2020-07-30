package com.hcl.onlinestore.dto;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.hcl.onlinestore.entity.Store;

public class ProductDto {

	private Integer productId;

	private String productName;

	private String productDescription;

	private Double productRating;

	private Store store;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductRating() {
		return productRating;
	}

	public void setProductRating(Double productRating) {
		this.productRating = productRating;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

}
