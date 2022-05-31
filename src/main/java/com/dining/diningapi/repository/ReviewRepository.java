package com.dining.diningapi.repository;

import com.dining.diningapi.model.Restaurant;
import com.dining.diningapi.model.Review;
import com.dining.diningapi.model.ReviewStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    List<Review> findAllByStatus(ReviewStatus status);
    List<Review> findAllByStatusAndRestaurantId(ReviewStatus status, Long restaurantId);
}
