package com.kamjritztex.bookkeeping.response.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.kamjritztex.bookkeeping.dto.AddressDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {
	
	private UUID id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDate dateOfBirth;
	private String gender;
	private String contactNumber;
	private AddressDto address;
    private Set<String> rolesName;
	

}
