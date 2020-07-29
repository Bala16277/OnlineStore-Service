package com.hcl.onlinestore.service;

import com.hcl.onlinestore.dto.ReviewRequestDto;
import com.hcl.onlinestore.dto.ReviewResponseDto;
import com.hcl.onlinestore.exception.InvalidOrderException;
import com.hcl.onlinestore.exception.InvalidReviewerException;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;

public interface ReviewService {

	public ReviewResponseDto giveRatings(Integer userId,ReviewRequestDto reviewRequestDto) throws UserNotFoundException, InvalidReviewerException, InvalidOrderException, ProductNotFoundException;
	
}
