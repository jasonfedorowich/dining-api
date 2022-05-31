package com.dining.diningapi.repository;

import com.dining.diningapi.model.Address;
import com.dining.diningapi.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    Restaurant findAllByAddress(Address address);

    Optional<Restaurant> findByAddressId(Long addressId);
}
