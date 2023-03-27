package com.cpallas.entities.dao;

import com.cpallas.dao.CreditCardRepository;
import com.cpallas.dto.CreditCardFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Status;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class CreditCardRepositoryIT extends BaseIntegrationTest {

    private final CreditCardRepository creditCardRepository;

    @Test
    void findAll() {
        List<CreditCard> creditCards = creditCardRepository.findAll();

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    void findByCreditCard() {
        CreditCardFilter filter = CreditCardFilter.builder().creditCardNumber(1L).build();
        CreditCard creditCard = creditCardRepository.findByFilter(filter);

        assertEquals(1L, creditCard.getCreditCardNumber());
    }

    @Test
    void findAllWithCredit() {
        List<CreditCard> creditCards = creditCardRepository.findAllWithCredit();

        assertEquals(2, creditCards.size());
        assertEquals(List.of(1L, 2L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    void findAllByStatus() {
        CreditCardFilter filter = CreditCardFilter.builder().status(Status.ACTIVE.getStatus()).build();
        List<CreditCard> creditCards = creditCardRepository.findAllByStatus(filter);

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }
}
