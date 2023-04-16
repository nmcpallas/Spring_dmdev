package com.cpallas.repository;

import com.cpallas.entities.BankAccount;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.time.LocalDate;
import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>, QuerydslPredicateExecutor<BankAccount> {
    /**
     * Возвращает банковский счет с указанным номером
     */
    BankAccount findByAccountNumber(long accountNumber);

    /**
     * Возвращает первые {limit} счетов, упорядоченных по дате создания (в порядке возрастания)
     */
    List<BankAccount> findByStartDateBetween(LocalDate from, LocalDate to, Pageable pageable);
}
