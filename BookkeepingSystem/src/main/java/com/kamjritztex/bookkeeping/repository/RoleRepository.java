package com.kamjritztex.bookkeeping.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Role;



@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {
	
	Optional<Role> findByName(String name);

}
