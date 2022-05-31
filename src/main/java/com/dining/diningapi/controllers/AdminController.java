package com.dining.diningapi.controllers;

import com.dining.diningapi.model.Restaurant;
import com.dining.diningapi.model.Review;
import com.dining.diningapi.model.ReviewFilterRequest;
import com.dining.diningapi.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAllReviews(){
        List<Review> reviews = new ArrayList<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    @PostMapping("/filter")
    public List<Review> getReviewByFilter(@RequestBody ReviewFilterRequest reviewFilterRequest){
        return reviewRepository.findAllByStatus(reviewFilterRequest.getStatus());
    }

    @PostMapping("/update/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId, @RequestBody ReviewFilterRequest reviewFilterRequest){
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if(reviewOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        Review review = reviewOptional.get();
        review.setStatus(reviewFilterRequest.getStatus());
        return reviewRepository.save(review);
    }


}
