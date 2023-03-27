package com.cpallas.entities.dao;

import com.cpallas.dto.BankAccountFilter;
import com.cpallas.dao.BankAccountRepository;
import com.cpallas.entities.BankAccount;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
public class BankAccountRepositoryIT extends BaseIntegrationTest {

    private final BankAccountRepository bankAccountRepository;

    @Test
    void findAll() {
        List<BankAccount> accounts = bankAccountRepository.findAll();

        assertEquals(3, accounts.size());

        List<Long> numbers = accounts.stream().map(BankAccount::getAccountNumber).toList();
        assertEquals(List.of(1L, 2L, 3L), numbers);
    }

    @Test
    void findByAccountNumber() {
        BankAccountFilter filter = BankAccountFilter.builder()
                .accountNumber(2L)
                .build();
        BankAccount account = bankAccountRepository.findByAccountNumber(filter);

        assertEquals(LocalDate.of(2000, 10, 20), account.getStartDate());
        assertEquals(LocalDate.of(2003, 10, 20), account.getEndDate());
    }

    @Test
    void findLimitedBunkAccountByIntervalDateCreate() {
        LocalDate from = LocalDate.of(2000, 10, 20);
        LocalDate to = LocalDate.of(2001, 10, 20);

        List<BankAccount> accounts = bankAccountRepository.findLimitedBunkAccountByIntervalDateCreate(from, to, 2);

        assertEquals(2, accounts.size());
        assertEquals(List.of(1L, 2L), accounts.stream().map(BankAccount::getAccountNumber).toList());
    }
}
