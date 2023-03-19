package com.cpallas.entities.dao;

import com.cpallas.dao.UserRepository;
import com.cpallas.dto.UserFilter;
import com.cpallas.entities.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserRepositoryIT extends BaseIntegrationTest {

    private final UserRepository userRepository = new UserRepository(session);

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();

        assertEquals(5, users.size());
    }

    @Test
    public void findByBankAccountNumber() {
        UserFilter filter = UserFilter.builder().accountNumber(1L).build();
        User user = userRepository.findByBankAccountNumber(filter);

        assertEquals("Anay", user.getName());
        assertEquals("Chan", user.getSurname());
    }
}
