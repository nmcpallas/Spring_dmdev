package com.cpallas.entities.dao;

import com.cpallas.dao.CreditCardRepository;
import com.cpallas.dto.CreditCardFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Status;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardRepositoryIT extends BaseIntegrationTest {

    private final CreditCardRepository creditCardRepository = new CreditCardRepository(session);

    @Test
    public void findAll() {
        List<CreditCard> creditCards = creditCardRepository.findAll();

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    public void findByCreditCard() {
        CreditCardFilter filter = CreditCardFilter.builder().creditCardNumber(1L).build();
        CreditCard creditCard = creditCardRepository.findByCreditCard(filter);

        assertEquals(1L, creditCard.getCreditCardNumber());
    }

    @Test
    public void findAllWithCredit() {
        List<CreditCard> creditCards = creditCardRepository.findAllWithCredit();

        assertEquals(2, creditCards.size());
        assertEquals(List.of(1L, 2L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    public void findAllByStatus() {
        CreditCardFilter filter = CreditCardFilter.builder().status(Status.ACTIVE.getStatus()).build();
        List<CreditCard> creditCards = creditCardRepository.findAllByStatus(filter);

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }
}
