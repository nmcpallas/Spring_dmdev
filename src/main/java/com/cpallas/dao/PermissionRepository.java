package com.cpallas.dao;

import com.cpallas.dto.PermissionFilter;
import com.cpallas.entities.CreditCard;
import com.cpallas.entities.Permission;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import java.util.List;

import static com.cpallas.entities.QPermission.permission;
import static com.cpallas.entities.QRole.role1;

public class PermissionRepository extends BaseRepository<Integer, Permission> {

    public PermissionRepository(EntityManager entityManager) {
        super(Permission.class, entityManager);
    }

    public List<Permission> findAllByRole(PermissionFilter filter) {
        return new JPAQuery<CreditCard>(entityManager).select(permission)
                .from(permission)
                .join(permission.role, role1).fetchJoin()
                .where(QPredicate.builder().add(filter.getRole(), role1.role::eq).buildAnd())
                .fetch();
    }
}