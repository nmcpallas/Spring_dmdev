package com.cpallas.mapper;

import com.cpallas.dto.BankAccountDto;
import com.cpallas.dto.CreditCardReadDto;
import com.cpallas.entities.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CreditCardReadMapper implements Mapper<CreditCard, CreditCardReadDto>{

    private final BankAccountReadMapper bankAccountReadMapper;

    public CreditCardReadDto map(CreditCard object) {
        BankAccountDto bankAccountDto = Optional.ofNullable(object.getBankAccount())
                .map(bankAccountReadMapper::map)
                .orElse(null);

        return new CreditCardReadDto(
                object.getId(),
                object.getCreditCardNumber(),
                object.getPin(),
                object.getCvv(),
                object.getCreditBalance(),
                object.getAmountBalance(),
                object.getCurrentBalance(),
                object.getMinimumPayment(),
                object.getMonthlyPayment(),
                object.getStartDate(),
                object.getEndDate(),
                object.getStatus(),
                bankAccountDto
        );
    }
}
