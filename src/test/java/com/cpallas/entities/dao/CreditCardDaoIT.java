package com.cpallas.entities.dao;

import com.cpallas.dao.CreditCardDao;
import com.cpallas.dto.CreditCardFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Status;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardDaoIT extends BaseDaoTest {

    @Test
    public void findAll() {
        List<CreditCard> creditCards = CreditCardDao.getInstance().findAll(session);

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    public void findByCreditCard() {
        CreditCardFilter filter = CreditCardFilter.builder().creditCardNumber(1L).build();
        CreditCard creditCard = CreditCardDao.getInstance().findByCreditCard(session, filter);

        assertEquals(1L, creditCard.getCreditCardNumber());
    }

    @Test
    public void findAllWithCredit() {
        List<CreditCard> creditCards = CreditCardDao.getInstance().findAllWithCredit(session);

        assertEquals(2, creditCards.size());
        assertEquals(List.of(1L, 2L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }

    @Test
    public void findAllByStatus() {
        CreditCardFilter filter = CreditCardFilter.builder().status(Status.ACTIVE.getStatus()).build();
        List<CreditCard> creditCards = CreditCardDao.getInstance().findAllByStatus(session, filter);

        assertEquals(3, creditCards.size());
        assertEquals(List.of(1L, 2L, 3L), creditCards.stream().map(CreditCard::getCreditCardNumber).toList());
    }
}
