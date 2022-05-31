package com.dining.diningapi.repository;

import com.dining.diningapi.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findByZipCode(String zipCode);

}
