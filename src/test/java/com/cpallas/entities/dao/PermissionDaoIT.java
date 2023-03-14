package com.cpallas.entities.dao;

import com.cpallas.dao.PermissionDao;
import com.cpallas.dto.PermissionFilter;
import com.cpallas.entities.Permission;
import com.cpallas.entities.RoleValue;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissionDaoIT extends BaseDaoTest {

    @Test
    public void findAll() {
        List<Permission> permissions = PermissionDao.getInstance().findAll(session);

        assertEquals(5, permissions.size());
    }

    @Test
    public void findAllByRole() {
        PermissionFilter filter = PermissionFilter.builder().role(RoleValue.ADMIN.getStatus()).build();
        List<Permission> permissions = PermissionDao.getInstance().findAllByRole(session, filter);

        assertEquals(2, permissions.size());
    }
}
