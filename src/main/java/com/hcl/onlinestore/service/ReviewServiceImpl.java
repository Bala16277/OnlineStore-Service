package com.hcl.onlinestore.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.onlinestore.config.ApplicationConstants;
import com.hcl.onlinestore.dto.ReviewRequestDto;
import com.hcl.onlinestore.dto.ReviewResponseDto;
import com.hcl.onlinestore.entity.OrderHistory;
import com.hcl.onlinestore.entity.Product;
import com.hcl.onlinestore.entity.ProductOrder;
import com.hcl.onlinestore.entity.Review;
import com.hcl.onlinestore.entity.User;
import com.hcl.onlinestore.exception.InvalidOrderException;
import com.hcl.onlinestore.exception.InvalidReviewerException;
import com.hcl.onlinestore.exception.ProductNotFoundException;
import com.hcl.onlinestore.exception.UserNotFoundException;
import com.hcl.onlinestore.repository.OrderHistoryRepository;
import com.hcl.onlinestore.repository.ProductOrderRepository;
import com.hcl.onlinestore.repository.ProductRepository;
import com.hcl.onlinestore.repository.ReviewRepository;
import com.hcl.onlinestore.repository.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	
	private static Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderHistoryRepository orderHistoryRepository;

	@Autowired
	ProductOrderRepository productOrderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ReviewRepository reviewRepository;

	public ReviewResponseDto giveRatings(Integer userId, ReviewRequestDto reviewRequestDto)
			throws UserNotFoundException, InvalidReviewerException, InvalidOrderException, ProductNotFoundException {
		ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
		Optional<User> users = userRepository.findByUserId(userId);
		Integer productId = reviewRequestDto.getProductId();
		logger.info("product id: "+productId);
		Optional<Product> products = productRepository.findByProductId(productId);
		if (users.isPresent()) {
			User user = users.get();
			logger.info("user id::: "+user.getUserId());
			if (products.isPresent()) {
				Product product = products.get();
				logger.info("product id: "+product.getProductId());
				Optional<ProductOrder> productOrders = productOrderRepository.findByProduct(product);
				
				if (productOrders.isPresent()) {
					
					ProductOrder productOrder = productOrders.get();
					
					logger.info("Product Order :"+productOrder.getProductOrderId());
					OrderHistory orderHistory = productOrder.getOrderHistory();
					logger.info("order history::: "+orderHistory.getOrderHistoryId());
					if (orderHistory.getUser().getUserId().equals(userId)) {
						Optional<Review> reviews = reviewRepository.findByProductAndUser(product, user);
						logger.info("reviews: "+reviews);
						if (!reviews.isPresent()) {
							
							Review review = new Review();
							logger.info("reviews: "+review.getReviewId());
							BeanUtils.copyProperties(reviewRequestDto, review);
							review.setProduct(product);
							review.setUser(orderHistory.getUser());
							reviewRepository.save(review);
							Double averageRating = (reviewRequestDto.getProductRating() + product.getProductRating())
									/ 2.0;
							product.setProductRating(averageRating);
							productRepository.save(product);
							reviewResponseDto.setStatusMessage(ApplicationConstants.REVIEW_SUCCESS);
							reviewResponseDto.setStatusCode(HttpStatus.OK.value());
						} else {
							logger.info("reviews: "+reviews.get().getReviewId());
							reviewResponseDto.setStatusMessage(ApplicationConstants.ALREADY_REVIEWED);
							reviewResponseDto.setStatusCode(HttpStatus.OK.value());
						}
					} else {
						throw new InvalidReviewerException(ApplicationConstants.INVALID_REVIEWER);
						// not a valid user to give a rating
					}

				} else {
					throw new InvalidOrderException(ApplicationConstants.INVALID_ORDER);
				}

			} else {
				throw new ProductNotFoundException(ApplicationConstants.PRODUCT_NOT_FOUND);
				// product Not found
			}

		} else {
			throw new UserNotFoundException(ApplicationConstants.USER_NOT_FOUND);
		}

		return reviewResponseDto;
	}

}
