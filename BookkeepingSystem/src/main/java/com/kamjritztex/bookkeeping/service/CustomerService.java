package com.kamjritztex.bookkeeping.service;

import java.util.List;
import java.util.Optional;

import com.kamjritztex.bookkeeping.dto.CustomerDto;
import com.kamjritztex.bookkeeping.dto.CustomerResponseDto;
import com.kamjritztex.bookkeeping.dto.UpdateCustomerDto;
import com.kamjritztex.bookkeeping.entity.Customer;


public interface CustomerService {



//	Customer createCustomer(String firstName, String lastName, String username, String password, String gender, String desingnation);

	public Optional<CustomerResponseDto> getCustomerById(String id);

	public Optional<CustomerResponseDto> getCustomerByEmail(String email);
	
	public List<CustomerResponseDto> getAllCustomers();

	public String deleteCustomer(String id, String updatedBy);
	
//	CustomerDto updateCustomer(String id, String firstName, String lastName, String email, String password);
	
	public CustomerResponseDto createCustomer(CustomerDto customerDto);
	
	public CustomerResponseDto updateCustomer(UpdateCustomerDto updateDto);
	
	
}
