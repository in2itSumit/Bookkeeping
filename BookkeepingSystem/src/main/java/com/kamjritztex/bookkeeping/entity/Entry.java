package com.kamjritztex.bookkeeping.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry extends Auditable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    
    private String transactionId;

    private String accountId;

    private boolean debit;
    private boolean credit;
}
