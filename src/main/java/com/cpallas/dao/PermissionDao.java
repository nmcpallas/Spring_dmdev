package com.cpallas.dao;

import com.cpallas.dto.PermissionFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Permission;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;

import java.util.List;

import static com.cpallas.entities.QPermission.permission;
import static com.cpallas.entities.QRole.role1;

public class PermissionDao {

    private static final PermissionDao INSTANCE = new PermissionDao();

    public List<Permission> findAll(Session session) {
        return new JPAQuery<CreditCard>(session).select(permission)
                .from(permission)
                .fetch();
    }

    public List<Permission> findAllByRole(Session session, PermissionFilter filter) {
        return new JPAQuery<CreditCard>(session).select(permission)
                .from(permission)
                .join(permission.role, role1).fetchJoin()
                .where(QPredicate.builder().add(filter.getRole(), role1.role::eq).buildAnd())
                .fetch();
    }

    public static PermissionDao getInstance() {
        return INSTANCE;
    }
}
