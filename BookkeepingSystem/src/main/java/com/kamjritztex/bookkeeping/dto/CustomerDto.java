package com.kamjritztex.bookkeeping.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.kamjritztex.bookkeeping.entity.Role;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private String gender;
	private String contact;
	private String createdBy;
	private AddressDto address;	
	private AccountDto account;
	

}
