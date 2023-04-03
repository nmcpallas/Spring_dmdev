package com.cpallas.dao;

import com.cpallas.entities.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RoleRepository extends AbstractRepositoryBase<Integer, Role> {

    public RoleRepository(EntityManager entityManager) {
        super(Role.class, entityManager);
    }
}
