package com.dining.diningapi.controllers;

import com.dining.diningapi.model.*;
import com.dining.diningapi.repository.AddressRepository;
import com.dining.diningapi.repository.RestaurantRepository;
import com.dining.diningapi.repository.ReviewRepository;
import com.dining.diningapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    private final AddressRepository addressRepository;

    @GetMapping
    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = new ArrayList<>();
        restaurantRepository.findAll().forEach(restaurants::add);
        return restaurants;
    }

    @PostMapping
    public Restaurant createNew(@RequestBody Restaurant restaurant){
        try{
            if(!restaurant.getReviews().isEmpty()){
                for (Review review : restaurant.getReviews()) {
                    review.setStatus(ReviewStatus.PENDING);
                }
            }
            return restaurantRepository.save(restaurant);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/add-review/{restaurant_id}/{user_id}")
    public Review addReview(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("user_id") Long userId, @RequestBody Review review){
        Optional<DiningUser> optionalDiningUser = userRepository.findById(userId);
        if(optionalDiningUser.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user present");
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if(restaurantOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No restaurant is present");

        review.setStatus(ReviewStatus.PENDING);
        review.setRestaurant(restaurantOptional.get());
        review.setUser(optionalDiningUser.get());
        review = reviewRepository.save(review);
        restaurantOptional.get().getReviews().add(review);
        restaurantRepository.save(restaurantOptional.get());
        return review;

    }

    @GetMapping("/address")
    public Restaurant getByAddress(@RequestParam String zipCode){
        Optional<Address> optionalAddress = addressRepository.findByZipCode(zipCode);
        if(optionalAddress.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        Optional<Restaurant> restaurantOptional = restaurantRepository.findByAddressId(optionalAddress.get().getId());
        if(restaurantOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return restaurantOptional.get();


    }

    @GetMapping("/reviews/{restaurantId}")
    public List<Review> getReviews(@PathVariable Long restaurantId){
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        List<Review> reviews = new ArrayList<>();
        for(Review review: optionalRestaurant.get().getReviews()){
            if(review.getStatus() == ReviewStatus.ACCEPTED){
                reviews.add(review);
            }
        }
        return reviews;
    }



}
