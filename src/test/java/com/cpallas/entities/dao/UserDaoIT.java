package com.cpallas.entities.dao;

import com.cpallas.dao.UserDao;
import com.cpallas.dto.UserFilter;
import com.cpallas.entities.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoIT extends BaseDaoTest {

    @Test
    public void findAll() {
        List<User> users = UserDao.getInstance().findAll(session);

        assertEquals(5, users.size());
    }

    @Test
    public void findByBankAccountNumber() {
        UserFilter filter = UserFilter.builder().accountNumber(1L).build();
        User user = UserDao.getInstance().findByBankAccountNumber(session, filter);

        assertEquals("Anay", user.getName());
        assertEquals("Chan", user.getSurname());
    }
}
