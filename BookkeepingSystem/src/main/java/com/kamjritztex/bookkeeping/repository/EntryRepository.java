package com.kamjritztex.bookkeeping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Entry;

@Repository
public interface EntryRepository extends MongoRepository<Entry, String>{

}
