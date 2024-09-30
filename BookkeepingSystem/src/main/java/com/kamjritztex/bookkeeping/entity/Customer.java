package com.kamjritztex.bookkeeping.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@NotNull
	@Size(min = 2, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 2, max = 50)
	private String lastName;

	@NotNull
	@Size(min = 5, max = 50)
	@Indexed(unique = true)
	private String email;

	@NotNull
	@Size(min = 8)
	private String password;
	@Past
	private LocalDate dateOfBirth;
	
	private String contact;

	private String gender;
	
	private boolean status;

	private String addressId;
	private String accountId;

	private Set<Role> roles;

	


}
