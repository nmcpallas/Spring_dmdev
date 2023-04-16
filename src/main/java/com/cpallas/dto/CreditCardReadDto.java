package com.cpallas.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreditCardReadDto {

    Integer id;
    Long creditCardNumber;
    Integer pin;
    Integer cvv;
    Integer creditBalance;
    Integer amountBalance;
    Integer currentBalance;
    Integer minimumPayment;
    Integer monthlyPayment;
    LocalDate startDate;
    LocalDate endDate;
    String status;
    BankAccountDto bankAccountDto;
}
