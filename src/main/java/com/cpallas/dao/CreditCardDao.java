package com.cpallas.dao;

import com.cpallas.dto.CreditCardFilter;
import com.cpallas.entities.CreditCard;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.List;

import static com.cpallas.entities.QCreditCard.creditCard;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCardDao {

    private static final CreditCardDao INSTANCE = new CreditCardDao();

    public List<CreditCard> findAll(Session session) {
        return new JPAQuery<CreditCard>(session).select(creditCard)
                .from(creditCard)
                .fetch();
    }

    public CreditCard findByCreditCard(Session session, CreditCardFilter filter) {
        return new JPAQuery<CreditCard>(session).select(creditCard)
                .from(creditCard)
                .where(QPredicate.builder().add(filter.getCreditCardNumber(), creditCard.creditCardNumber::eq).buildOr())
                .fetchOne();
    }

    public List<CreditCard> findAllWithCredit(Session session) {
        return new JPAQuery<CreditCard>(session).select(creditCard)
                .from(creditCard)
                .where(creditCard.creditBalance.between(1, Integer.MAX_VALUE))
                .fetch();
    }

    public List<CreditCard> findAllByStatus(Session session, CreditCardFilter filter) {
        return new JPAQuery<CreditCard>(session).select(creditCard)
                .from(creditCard)
                .where(QPredicate.builder().add(filter.getStatus(), creditCard.status::eq).buildOr())
                .fetch();
    }

    public static CreditCardDao getInstance() {
        return INSTANCE;
    }
}
