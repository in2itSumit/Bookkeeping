package com.kamjritztex.bookkeeping.dto;

import java.util.HashSet;

import com.kamjritztex.bookkeeping.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
	
	private int id;
	
	private String name;
	

	private HashSet<Customer> customer;

}
