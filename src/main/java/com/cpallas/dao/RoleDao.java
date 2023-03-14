package com.cpallas.dao;

import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Role;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static com.cpallas.entities.QRole.role1;

public class RoleDao {

    private static final RoleDao INSTANCE = new RoleDao();

    public List<Role> findAll(Session session) {
        return new JPAQuery<CreditCard>(session).select(role1)
                .from(role1)
                .fetch();
    }

    public static RoleDao getInstance() {
        return INSTANCE;
    }
}
