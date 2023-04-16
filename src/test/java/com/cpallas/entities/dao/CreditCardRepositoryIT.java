package com.cpallas.entities.dao;

import com.cpallas.repository.CreditCardRepository;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardRepositoryIT extends BaseIntegrationTest {

    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardRepositoryIT(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Test
    void findAll() {
        List<CreditCard> creditCards = creditCardRepository.findAll();

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    void findByCreditCard() {
        CreditCard creditCard = creditCardRepository.findByCreditCardNumber(1L);

        assertEquals(1L, creditCard.getCreditCardNumber());
    }

    @Test
    void findAllWithCredit() {
        List<CreditCard> creditCards = creditCardRepository.findAllByCreditBalanceBetween(1, Integer.MAX_VALUE);

        assertEquals(2, creditCards.size());
        assertEquals(List.of(1L, 2L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    void findAllByStatus() {
        List<CreditCard> creditCards = creditCardRepository.findAllByStatus(Status.ACTIVE.getStatus());

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }
}
