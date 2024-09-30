package com.kamjritztex.bookkeeping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {
	
    private CustomerResponseDto customer;

    private TransactionDto transaction;

}
