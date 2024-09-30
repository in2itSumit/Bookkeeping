package com.kamjritztex.bookkeeping.entity;

import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "permissions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Permission extends Auditable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	@NotBlank
	@Size(max = 100)
	@Indexed(unique = true)
	private String permission;
	
    private Set<Role> roles;

}
