package com.cpallas.entities.dao;

import com.cpallas.dao.PermissionRepository;
import com.cpallas.dto.PermissionFilter;
import com.cpallas.entities.Permission;
import com.cpallas.entities.RoleValue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissionRepositoryIT extends BaseIntegrationTest {

    private final PermissionRepository permissionRepository = new PermissionRepository(session);

    @Test
    void findAll() {
        List<Permission> permissions = permissionRepository.findAll();

        assertEquals(5, permissions.size());
    }

    @Test
    void findAllByRole() {
        PermissionFilter filter = PermissionFilter.builder().role(RoleValue.ADMIN.getStatus()).build();
        List<Permission> permissions = permissionRepository.findAllByRole(filter);

        assertEquals(2, permissions.size());
    }
}
