package com.cpallas.entities.crud;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.Role;
import com.cpallas.entities.User;
import com.cpallas.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

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

        session.flush();
        session.clear();
        User actualUser = session.get(User.class, 1);

        assertEquals(user, actualUser);

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

        session.save(user);
        session.flush();
        session.clear();

        user.setName("new");
        session.update(user);
        session.flush();
        session.clear();

        User actualUser = session.get(User.class, 1);

        assertEquals(user, actualUser);

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
