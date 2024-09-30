package com.kamjritztex.bookkeeping.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {

    private LocalDateTime dateTime;
    private String description;
    
    private String customerId;

    private long amount;
}
