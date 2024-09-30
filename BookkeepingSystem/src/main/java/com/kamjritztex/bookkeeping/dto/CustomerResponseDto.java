package com.kamjritztex.bookkeeping.dto;

import java.time.LocalDate;
import java.util.Set;

import com.kamjritztex.bookkeeping.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private String gender;
	private String contact;
	private AddressResponseDto address;	
	private AccountResponseDto account;
    private Set<Role> roles;
	

}
