package com.kamjritztex.bookkeeping.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.kamjritztex.bookkeeping.entity.Address;


@Repository
public interface AddressRepository extends MongoRepository<Address, String>{
	
	List<Address> findByArea(String area);
	List<Address> findByCountry(String country);
	List<Address> findByHouseNo(String houseNo);
	List<Address> findByDistrict(String district);
	List<Address> findByState(String state);
	List<Address> findByPinCode(int pinCode);

}
