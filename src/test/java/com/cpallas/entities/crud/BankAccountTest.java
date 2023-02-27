package com.cpallas.entities.crud;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Role;
import com.cpallas.entities.Status;
import com.cpallas.entities.User;
import com.cpallas.util.HibernateUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class BankAccountTest {

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
        session.flush();
        session.clear();
        BankAccount actualAccount = session.get(BankAccount.class, 1);

        assertEquals(bankAccount, actualAccount);

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
        bankAccount.setCreditCards(List.of(creditCard));
        session.update(bankAccount);
        session.flush();
        session.clear();

        BankAccount actualAccount = session.get(BankAccount.class, 1);

        assertEquals(bankAccount, actualAccount);

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
        session.flush();
        session.clear();
        BankAccount actualAccount = session.get(BankAccount.class, 1);

        session.delete(actualAccount);
        session.flush();
        session.clear();

        Role role = session.get(Role.class, 1);

        assertNull(role);

        session.getTransaction().commit();
    }
}
