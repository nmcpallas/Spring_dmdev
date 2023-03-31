package com.cpallas.dao;

import com.cpallas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, Integer>, QuerydslPredicateExecutor<User> {

    @Query(value = "select u from User u " +
            "join u.bankAccounts b where b.accountNumber = :bankAccountNumber")
    User findByBankAccountNumber(long bankAccountNumber);
}
