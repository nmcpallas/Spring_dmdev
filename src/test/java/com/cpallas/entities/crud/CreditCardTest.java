package com.cpallas.entities.crud;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Status;
import com.cpallas.entities.User;
import com.cpallas.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreditCardTest {

    @Test
    void create() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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
        session.flush();
        session.clear();

        CreditCard actualCreditCard = session.get(CreditCard.class, 1);

        assertEquals(creditCard, actualCreditCard);

        session.getTransaction().commit();
    }

    @Test
    void update() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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
        session.flush();
        session.clear();

        creditCard.setCreditCardNumber(5556000012121001L);
        session.update(creditCard);
        session.flush();
        session.clear();

        CreditCard actualCreditCard = session.get(CreditCard.class, 1);

        assertEquals(creditCard, actualCreditCard);

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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
        session.flush();
        session.clear();

        session.delete(creditCard);

        CreditCard actualCreditCard = session.get(CreditCard.class, 1);

        assertNull(actualCreditCard);

        session.getTransaction().commit();
    }
}
