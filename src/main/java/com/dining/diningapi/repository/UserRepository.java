package com.dining.diningapi.repository;

import com.dining.diningapi.model.DiningUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<DiningUser, Long> {

    Optional<DiningUser> findByName(String name);
}
