package com.hcl.onlinestore.dto;

public class ReviewRequestDto {

	private String reviewComment;

	private Integer productRating;

	private Integer storeRating;

	private Integer productId;

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

	public Integer getProductRating() {
		return productRating;
	}

	public void setProductRating(Integer productRating) {
		this.productRating = productRating;
	}

	public Integer getStoreRating() {
		return storeRating;
	}

	public void setStoreRating(Integer storeRating) {
		this.storeRating = storeRating;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
