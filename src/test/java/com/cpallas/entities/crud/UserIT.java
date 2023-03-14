package com.cpallas.entities.crud;

import com.cpallas.entities.BankAccount;
import com.cpallas.entities.BaseTest;
import com.cpallas.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserIT extends BaseTest {

    @Test
    void createAndGet() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        session.flush();
        session.clear();

        User actualUser = session.get(User.class, user.getId());

        assertEquals(user, actualUser);
    }

    @Test
    void update() {
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

        User actualUser = session.get(User.class, user.getId());

        assertEquals(user, actualUser);
    }

    @Test
    void delete() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        session.save(user);
        session.flush();
        session.clear();

        session.delete(user);
        session.flush();
        session.clear();

        BankAccount actualAccount = session.get(BankAccount.class, user.getId());

        assertNull(actualAccount);
    }
}
