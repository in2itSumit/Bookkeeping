package com.kamjritztex.bookkeeping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kamjritztex.bookkeeping.dto.CustomerDto;
import com.kamjritztex.bookkeeping.dto.CustomerResponseDto;
import com.kamjritztex.bookkeeping.dto.UpdateCustomerDto;
import com.kamjritztex.bookkeeping.service.CustomerService;


@RestController
@RequestMapping("bookkeeping/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("create")
	public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerDto customerDto) {
		
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}
	@PutMapping("update")
	public ResponseEntity<CustomerResponseDto> updateCustomer(@RequestBody UpdateCustomerDto updateDto) {
		
		return ResponseEntity.ok(customerService.updateCustomer(updateDto));
	}
	@GetMapping("get-by-id/{id}")
	public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable("id")String id) {
		return ResponseEntity.ok(customerService.getCustomerById(id).get());
	}
	@GetMapping("get-by-email/{email}")
	public ResponseEntity<CustomerResponseDto> getCustomerEmail(@PathVariable("email")String email) {
		
		return ResponseEntity.ok(customerService.getCustomerByEmail(email).get());
	}
	@GetMapping("get-all")
	public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
		
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	@DeleteMapping("delete-by-id/{id}/{updatedBy}")
	public ResponseEntity<String> deleteCustomer(@PathVariable("id") String id, @PathVariable("updatedBy") String updatedBy) {
		
		return ResponseEntity.ok(customerService.deleteCustomer(id,updatedBy));
	}
	
}
