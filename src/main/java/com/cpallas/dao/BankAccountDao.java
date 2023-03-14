package com.cpallas.dao;

import com.cpallas.dto.BankAccountFilter;
import com.cpallas.entities.BankAccount;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

import static com.cpallas.entities.QBankAccount.bankAccount;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountDao {

    private static final BankAccountDao INSTANCE = new BankAccountDao();

    /**
     * Возвращает все банковские счета
     */
    public List<BankAccount> findAll(Session session) {
        return new JPAQuery<BankAccount>(session).select(bankAccount)
                .from(bankAccount)
                .fetch();
    }

    /**
     * Возвращает банковский счет с указанным номером
     */
    public BankAccount findByAccountNumber(Session session, BankAccountFilter filter) {
        Predicate predicate = QPredicate.builder()
                .add(filter.getAccountNumber(), bankAccount.accountNumber::eq)
                .buildAnd();

        return new JPAQuery<BankAccount>(session).select(bankAccount)
                .from(bankAccount)
                .where(predicate)
                .fetchOne();
    }

    /**
     * Возвращает первые {limit} счетов, упорядоченных по дате создания (в порядке возрастания)
     */
    public List<BankAccount> findLimitedBunkAccountByIntervalDateCreate(Session session, LocalDate from, LocalDate to, int limit) {
        return new JPAQuery<BankAccount>(session).select(bankAccount)
                .from(bankAccount)
                .where(bankAccount.startDate.between(from, to))
                .limit(limit)
                .fetch();
    }

    public static BankAccountDao getInstance() {
        return INSTANCE;
    }
}
