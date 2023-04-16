package com.cpallas.dao;

import com.cpallas.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Integer>, QuerydslPredicateExecutor<CreditCard> {

    CreditCard findByCreditCardNumber(Long creditCardNumber);

    List<CreditCard> findAllByCreditBalanceBetween(Integer more, Integer less);

    List<CreditCard> findAllByStatus(String status);
}
