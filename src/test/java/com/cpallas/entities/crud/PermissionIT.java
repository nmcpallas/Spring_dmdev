package com.cpallas.entities.crud;

import com.cpallas.entities.BaseTest;
import com.cpallas.entities.Permission;
import com.cpallas.entities.Role;
import com.cpallas.entities.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PermissionIT extends BaseTest {

    @Test
    void createAndGet() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        Role role = Role.builder()
                .role("admin")
                .build();
        session.save(role);

        Permission permission = Permission.builder()
                .login("login")
                .passowrd("pass")
                .user(user)
                .role(role)
                .build();

        session.save(permission);
        session.flush();
        session.clear();

        Permission actualPermission = session.get(Permission.class, permission.getId());

        assertEquals(permission, actualPermission);
    }

    @Test
    void update() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        Role role = Role.builder()
                .role("admin")
                .build();
        session.save(role);

        Permission permission = Permission.builder()
                .login("login")
                .passowrd("pass")
                .user(user)
                .role(role)
                .build();

        session.save(permission);
        session.flush();
        session.clear();

        permission.setPassowrd("password");
        permission.getRole().setRole("user");
        permission.getUser().setName("name");
        session.update(permission);

        Permission actualPermission = session.get(Permission.class, permission.getId());

        assertEquals(permission, actualPermission);
    }

    @Test
    void delete() {
        User user = User.builder()
                .name("test")
                .surname("test")
                .build();
        session.save(user);

        Role role = Role.builder()
                .role("admin")
                .build();
        session.save(role);

        Permission permission = Permission.builder()
                .login("login")
                .passowrd("pass")
                .user(user)
                .role(role)
                .build();

        session.save(permission);
        session.flush();
        session.clear();

        session.delete(permission);
        session.flush();
        session.clear();

        Permission actualPermission = session.get(Permission.class, permission.getId());

        assertNull(actualPermission);
    }
}
