package com.kamjritztex.bookkeeping.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "transactions")
@NoArgsConstructor
@AllArgsConstructor 
@Data
public class Transaction extends Auditable{

	 	@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private String id;
	    private LocalDateTime dateTime;
	    private String description;
	    
	    private String customerId;

	    private long amount;
}
