package com.kamjritztex.bookkeeping.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	
	private String houseNo;
	private String streetNo;
	private String area;
	private String district;
	private String state;
	private String country;
	private int pinCode;
	

}
