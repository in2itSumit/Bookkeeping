package com.kamjritztex.bookkeeping.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "account")
@NoArgsConstructor
@AllArgsConstructor 
@Data
public class Account extends Auditable{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private String id;
	    private String name;
	    private String type; //saving, Current
	    private double balance;
}
