package com.cpallas.entities.dao;

import com.cpallas.dto.BankAccountFilter;
import com.cpallas.dao.BankAccountDao;
import com.cpallas.entities.BankAccount;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountDaoIT extends BaseDaoTest {

    private final BankAccountDao bankAccountDao = BankAccountDao.getInstance();

    @Test
    public void findAll() {
        List<BankAccount> accounts = bankAccountDao.findAll(session);

        assertEquals(3, accounts.size());

        List<Long> numbers = accounts.stream().map(BankAccount::getAccountNumber).toList();
        assertEquals(List.of(1L, 2L, 3L), numbers);
    }

    @Test
    public void findByAccountNumber() {
        BankAccountFilter filter = BankAccountFilter.builder()
                .accountNumber(2L)
                .build();
        BankAccount account = bankAccountDao.findByAccountNumber(session, filter);

        assertEquals(LocalDate.of(2000, 10, 20), account.getStartDate());
        assertEquals(LocalDate.of(2003, 10, 20), account.getEndDate());
    }

    @Test
    public void findLimitedBunkAccountByIntervalDateCreate() {
        LocalDate from = LocalDate.of(2000, 10, 20);
        LocalDate to = LocalDate.of(2001, 10, 20);

        List<BankAccount> accounts = bankAccountDao.findLimitedBunkAccountByIntervalDateCreate(session, from, to, 2);

        assertEquals(2, accounts.size());
        assertEquals(List.of(1L, 2L), accounts.stream().map(BankAccount::getAccountNumber).toList());
    }
}
