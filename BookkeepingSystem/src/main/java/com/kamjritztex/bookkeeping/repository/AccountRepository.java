package com.kamjritztex.bookkeeping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String>{

}
