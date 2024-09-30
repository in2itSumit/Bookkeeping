package com.kamjritztex.bookkeeping.entity;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "address")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;


	@NotBlank
	@Size(max = 20)
	private String houseNo;

	@Size(max = 20)
	private String streetNo;
	@Size(max = 100)
	private String area;
	@Size(max = 100)
	private String district;
	@Size(max = 100)
	private String state;
	@Size(max = 100)
	private String country;
	@Min(100000) 						// Assuming Indian PIN code format
	@Max(999999) 						// PIN codes are usually 6 digits
	private int pinCode;

}
