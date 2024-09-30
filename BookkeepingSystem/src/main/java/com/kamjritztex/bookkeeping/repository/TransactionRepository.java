package com.kamjritztex.bookkeeping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{

}
