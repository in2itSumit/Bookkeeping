package com.kamjritztex.bookkeeping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data
public class AccountDto {

    private String name;
    private String type; //saving, Current
    private double balance;
}
