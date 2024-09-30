package com.kamjritztex.bookkeeping.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Customer;




@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

	Optional<Customer> findByEmailAndStatus(String email, boolean status);
	Optional<Customer> findByIdAndStatus(String id, boolean status);
	
}
