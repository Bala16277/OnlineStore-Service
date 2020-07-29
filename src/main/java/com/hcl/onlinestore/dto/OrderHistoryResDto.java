package com.hcl.onlinestore.dto;

public class OrderHistoryResDto {

	private int orderHistoryId;
	
	private double totalCost;

	public int getOrderHistoryId() {
		return orderHistoryId;
	}

	public void setOrderHistoryId(int orderHistoryId) {
		this.orderHistoryId = orderHistoryId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
}
