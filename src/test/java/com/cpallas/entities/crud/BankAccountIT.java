package com.cpallas.entities.crud;

import com.cpallas.dto.BankAccountFilter;
import com.cpallas.dao.BankAccountDao;
import com.cpallas.entities.BankAccount;
import com.cpallas.entities.BaseTest;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Status;
import com.cpallas.entities.User;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class BankAccountIT extends BaseTest {

    @Test
    void createAndGet() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        BankAccount bankAccount = BankAccount.builder()
                .accountNumber(1241241425L)
                .balance(0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .user(user)
                .build();

        session.save(bankAccount);
        session.flush();
        session.clear();
        BankAccountFilter filter = BankAccountFilter.builder().accountNumber(bankAccount.getAccountNumber()).build();
        BankAccount actualAccount = BankAccountDao.getInstance().findByAccountNumber(session, filter);

        assertEquals(bankAccount, actualAccount);
    }

    @Test
    void update() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        BankAccount bankAccount = BankAccount.builder()
                .accountNumber(1241241425L)
                .balance(0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .user(user)
                .build();

        session.save(bankAccount);
        session.flush();
        session.clear();

        CreditCard creditCard = CreditCard.builder()
                .creditCardNumber(5556000012121000L)
                .pin(9050)
                .ccv(960)
                .creditBalance(0)
                .amountBalance(60000)
                .currentBalance(60000)
                .minimumPayment(0)
                .monthlyPayment(0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .status(Status.ACTIVE.getStatus())
                .bankAccount(bankAccount)
                .build();

        session.save(creditCard);
        bankAccount.addCreditCard(creditCard);
        session.update(bankAccount);
        session.flush();
        session.clear();

        BankAccountFilter filter = BankAccountFilter.builder().accountNumber(bankAccount.getAccountNumber()).build();
        BankAccount actualAccount = BankAccountDao.getInstance().findByAccountNumber(session, filter);

        assertEquals(bankAccount, actualAccount);
    }

    @Test
    void delete() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        BankAccount bankAccount = BankAccount.builder()
                .accountNumber(1241241425L)
                .balance(0)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .user(user)
                .build();

        session.save(bankAccount);
        session.flush();
        session.clear();

        session.delete(bankAccount);
        session.flush();
        session.clear();

        BankAccountFilter filter = BankAccountFilter.builder().accountNumber(bankAccount.getAccountNumber()).build();
        BankAccount actualAccount = BankAccountDao.getInstance().findByAccountNumber(session, filter);

        assertNull(actualAccount);
    }
}
