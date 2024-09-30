package com.kamjritztex.bookkeeping.response.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerResponseDto {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private String gender;
	private Set<String> rolesName; 

}
