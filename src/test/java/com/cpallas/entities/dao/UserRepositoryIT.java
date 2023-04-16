package com.cpallas.entities.dao;

import com.cpallas.dao.UserRepository;
import com.cpallas.entities.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryIT extends BaseIntegrationTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryIT(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();

        assertEquals(5, users.size());
    }

    @Test
    void findByBankAccountNumber() {
        User user = userRepository.findByBankAccountNumber(1L);

        assertEquals("Anay", user.getName());
        assertEquals("Chan", user.getSurname());
    }
}
