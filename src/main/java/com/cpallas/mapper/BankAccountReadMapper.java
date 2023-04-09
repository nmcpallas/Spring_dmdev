package com.cpallas.mapper;

import com.cpallas.dto.BankAccountDto;
import com.cpallas.entities.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountReadMapper implements Mapper<BankAccount, BankAccountDto> {

    public BankAccountDto map(BankAccount object) {
        return new BankAccountDto(object.getId(),
                object.getAccountNumber());
    }
}
