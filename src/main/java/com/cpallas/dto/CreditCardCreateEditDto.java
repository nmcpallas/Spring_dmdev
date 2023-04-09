package com.cpallas.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Value
@FieldNameConstants
public class CreditCardCreateEditDto {

    Long creditCardNumber;

    Integer pin;

    Integer ccv;

    Integer creditBalance;

    Integer amountBalance;

    Integer currentBalance;

    Integer minimumPayment;

    Integer monthlyPayment;

    LocalDate startDate;

    LocalDate endDate;

    String status;

    Integer bankAccountId;
}
