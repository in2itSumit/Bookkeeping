package com.kamjritztex.bookkeeping.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Permission;



@Repository
public interface PermissionRepository extends MongoRepository<Permission, Integer>{
	
	Optional<Permission> findByPermission(String permission);

}
