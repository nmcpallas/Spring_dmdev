package com.cpallas.mapper;

import com.cpallas.dto.CreditCardCreateEditDto;
import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CreditCardCreateEditMapper implements Mapper<CreditCardCreateEditDto, CreditCard>{

    private final BankAccountRepository repository;

    @Override
    public CreditCard map(CreditCardCreateEditDto object) {
        CreditCard card = new CreditCard();
        copy(object, card);

        return card;
    }

    @Override
    public CreditCard map(CreditCardCreateEditDto fromObject, CreditCard toObject) {
        copy(fromObject, toObject);

        return toObject;
    }

    private void copy(CreditCardCreateEditDto object, CreditCard creditCard) {
        creditCard.setCreditCardNumber(object.getCreditCardNumber());
        creditCard.setPin(object.getPin());
        creditCard.setCvv(object.getCvv());
        creditCard.setCreditBalance(object.getCreditBalance());
        creditCard.setAmountBalance(object.getAmountBalance());
        creditCard.setCurrentBalance(object.getCurrentBalance());
        creditCard.setMinimumPayment(object.getMinimumPayment());
        creditCard.setMonthlyPayment(object.getMonthlyPayment());
        creditCard.setStartDate(object.getStartDate());
        creditCard.setEndDate(object.getEndDate());
        creditCard.setStatus(object.getStatus());
        creditCard.setBankAccount(getBankAccount(object.getBankAccountId()));
    }

    public BankAccount getBankAccount(Integer id) {
        return Optional.ofNullable(id)
                .flatMap(repository::findById)
                .orElse(null);
    }
}
