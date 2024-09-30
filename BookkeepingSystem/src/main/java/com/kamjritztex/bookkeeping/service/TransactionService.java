package com.kamjritztex.bookkeeping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kamjritztex.bookkeeping.dto.TransactionDto;

@Service
public interface TransactionService 
{
	public String doTransaction(double amount, String accountId);
	public List<TransactionDto> getTransactionByTransactionId(String id);
	
}
