package com.cpallas.repository;

import com.cpallas.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RoleRepository extends JpaRepository<Role, Integer>, QuerydslPredicateExecutor<Role> {
}
