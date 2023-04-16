package com.cpallas.entities.dao;

import com.cpallas.dao.PermissionRepository;
import com.cpallas.entities.Permission;
import com.cpallas.entities.RoleValue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissionRepositoryIT extends BaseIntegrationTest {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionRepositoryIT(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Test
    void findAll() {
        List<Permission> permissions = permissionRepository.findAll();

        assertEquals(5, permissions.size());
    }

    @Test
    void findAllByRole() {
        List<Permission> permissions = permissionRepository.findAllByRole(RoleValue.ADMIN.getRole());

        assertEquals(2, permissions.size());
    }
}
