package com.cpallas.dao;

import com.cpallas.dto.UserFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.User;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.cpallas.entities.QBankAccount.bankAccount;
import static com.cpallas.entities.QUser.user;

@Repository
public class UserRepository extends AbstractRepositoryBase<Integer, User> {

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
    }

    public User findByBankFilter(UserFilter filter) {
        return new JPAQuery<CreditCard>(entityManager).select(user)
                .from(user)
                .join(user.bankAccounts, bankAccount).fetchJoin()
                .where(QPredicate.builder().add(filter.getAccountNumber(), bankAccount.accountNumber::eq).buildAnd())
                .fetchOne();
    }
}
