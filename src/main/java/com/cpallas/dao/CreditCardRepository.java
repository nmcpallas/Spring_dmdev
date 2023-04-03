package com.cpallas.dao;

import com.cpallas.dto.CreditCardFilter;
import com.cpallas.entities.CreditCard;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cpallas.entities.QCreditCard.creditCard;

@Repository
public class CreditCardRepository extends AbstractRepositoryBase<Integer, CreditCard> {

    public CreditCardRepository(EntityManager entityManager) {
        super(CreditCard.class, entityManager);
    }

    public CreditCard findByFilter(CreditCardFilter filter) {
        return new JPAQuery<CreditCard>(entityManager).select(creditCard)
                .from(creditCard)
                .where(QPredicate.builder().add(filter.getCreditCardNumber(), creditCard.creditCardNumber::eq).buildOr())
                .fetchOne();
    }

    public List<CreditCard> findAllWithCredit() {
        return new JPAQuery<CreditCard>(entityManager).select(creditCard)
                .from(creditCard)
                .where(creditCard.creditBalance.between(1, Integer.MAX_VALUE))
                .fetch();
    }

    public List<CreditCard> findAllByStatus(CreditCardFilter filter) {
        return new JPAQuery<CreditCard>(entityManager).select(creditCard)
                .from(creditCard)
                .where(QPredicate.builder().add(filter.getStatus(), creditCard.status::eq).buildOr())
                .fetch();
    }
}
