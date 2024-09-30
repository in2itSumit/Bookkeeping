package com.kamjritztex.bookkeeping.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCustomerDto {
	
	private String id;
	private String firstName;
	private String lastName;
	private String password;
	private LocalDate dateOfBirth;
	private String gender;
	private String contact;
	private String updatedBy;
	
	private AddressDto address;

}
