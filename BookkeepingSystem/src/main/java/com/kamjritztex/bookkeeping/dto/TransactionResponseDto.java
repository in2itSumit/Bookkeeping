package com.kamjritztex.bookkeeping.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {

    private String id;
    private LocalDateTime dateTime;
    private String description;
    
    private String customerId;

    private long amount;
}