package com.cpallas.dao;

import com.cpallas.dto.BankAccountFilter;
import com.cpallas.entities.BankAccount;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.cpallas.entities.QBankAccount.bankAccount;

public class BankAccountRepository extends BaseRepository<Integer, BankAccount>{

    public BankAccountRepository(EntityManager entityManager) {
        super(BankAccount.class, entityManager);
    }

    /**
     * Возвращает банковский счет с указанным номером
     */
    public BankAccount findByAccountNumber(BankAccountFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getAccountNumber(), bankAccount.accountNumber::eq)
                .buildAnd();

        return new JPAQuery<BankAccount>(entityManager).select(bankAccount)
                .from(bankAccount)
                .where(predicate)
                .fetchOne();
    }

    /**
     * Возвращает первые {limit} счетов, упорядоченных по дате создания (в порядке возрастания)
     */
    public List<BankAccount> findLimitedBunkAccountByIntervalDateCreate(LocalDate from, LocalDate to, int limit) {
        return new JPAQuery<BankAccount>(entityManager).select(bankAccount)
                .from(bankAccount)
                .where(bankAccount.startDate.between(from, to))
                .limit(limit)
                .fetch();
    }
}
