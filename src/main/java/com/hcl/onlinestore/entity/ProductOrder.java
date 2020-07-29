package com.hcl.onlinestore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ProductOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productOrderId;
	
	@OneToOne
	@JoinColumn(name = "productId")
	private Product product;
	
	private Integer quantity;
	
	@OneToOne
	@JoinColumn(name = "orderHistoryId")
	private OrderHistory orderHistory;
	
}
