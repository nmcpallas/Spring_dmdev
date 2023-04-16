package com.cpallas.entities.dao;

import com.cpallas.dao.BankAccountRepository;
import com.cpallas.entities.BankAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountRepositoryIT extends BaseIntegrationTest {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountRepositoryIT(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Test
    void findAll() {
        List<BankAccount> accounts = bankAccountRepository.findAll();

        assertEquals(3, accounts.size());

        List<Long> numbers = accounts.stream().map(BankAccount::getAccountNumber).toList();
        assertEquals(List.of(1L, 2L, 3L), numbers);
    }

    @Test
    void findByAccountNumber() {
        BankAccount account = bankAccountRepository.findByAccountNumber(2L);

        assertEquals(LocalDate.of(2000, 10, 20), account.getStartDate());
        assertEquals(LocalDate.of(2003, 10, 20), account.getEndDate());
    }

    @Test
    void findLimitedBankAccountByIntervalDateCreate() {
        LocalDate from = LocalDate.of(2000, 10, 20);
        LocalDate to = LocalDate.of(2001, 10, 20);
        PageRequest pageable = PageRequest.of(0, 2);

        List<BankAccount> accounts = bankAccountRepository.findByStartDateBetween(from, to, pageable);

        assertEquals(2, accounts.size());
        assertEquals(List.of(1L, 2L), accounts.stream().map(BankAccount::getAccountNumber).toList());
    }
}
