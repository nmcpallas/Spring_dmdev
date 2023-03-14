package com.cpallas.dao;

import com.cpallas.dto.UserFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static com.cpallas.entities.QBankAccount.bankAccount;
import static com.cpallas.entities.QUser.user;

public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        return new JPAQuery<CreditCard>(session).select(user)
                .from(user)
                .fetch();
    }

    public User findByBankAccountNumber(Session session, UserFilter filter) {
        return new JPAQuery<CreditCard>(session).select(user)
                .from(user)
                .join(user.bankAccounts, bankAccount).fetchJoin()
                .where(QPredicate.builder().add(filter.getAccountNumber(), bankAccount.accountNumber::eq).buildAnd())
                .fetchOne();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
