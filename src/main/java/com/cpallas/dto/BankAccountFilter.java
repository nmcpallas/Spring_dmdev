package com.cpallas.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BankAccountFilter {

    Long accountNumber;
    LocalDate startDate;
    LocalDate endDate;
}
