package com.cpallas.entities.crud;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Role;
import com.cpallas.entities.Status;
import com.cpallas.entities.User;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class BankAccountIT extends BaseTest{

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
        BankAccount actualAccount = session.get(BankAccount.class, bankAccount.getId());

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
        bankAccount.getCreditCards().add(creditCard);
        session.update(bankAccount);
        session.flush();
        session.clear();

        BankAccount actualAccount = session.get(BankAccount.class, bankAccount.getId());

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

        BankAccount actualAccount = session.get(BankAccount.class, bankAccount.getId());

        assertNull(actualAccount);
    }
}
