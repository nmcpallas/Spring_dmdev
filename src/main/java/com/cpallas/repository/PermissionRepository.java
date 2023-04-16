package com.cpallas.repository;

import com.cpallas.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, QuerydslPredicateExecutor<Permission> {

    @Query(value = "select p from Permission p " +
            "join p.role r where r.role = :role")
    List<Permission> findAllByRole(String role);
}
