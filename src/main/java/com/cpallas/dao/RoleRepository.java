package com.cpallas.dao;

import com.cpallas.entities.Role;

import javax.persistence.EntityManager;

public class RoleRepository extends BaseRepository<Integer, Role> {

    public RoleRepository(EntityManager entityManager) {
        super(Role.class, entityManager);
    }
}
