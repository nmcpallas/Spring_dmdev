package com.cpallas.entities.crud;

import com.cpallas.entities.BaseTest;
import com.cpallas.entities.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class RoleIT extends BaseTest {

    @Test
    void createAndRead() {
        Role admin = Role.builder()
                .role("admin")
                .build();

        session.save(admin);
        session.flush();
        session.clear();

        Role role = session.get(Role.class, admin.getId());

        assertEquals(admin, role);
        assertEquals(admin.getRole(), role.getRole());
    }

    @Test
    void update() {
        Role admin = Role.builder()
                .role("admin")
                .build();

        session.save(admin);
        session.flush();
        session.clear();

        admin.setRole("user");
        session.update(admin);
        session.flush();
        session.clear();

        Role actual = session.get(Role.class, admin.getId());

        assertEquals(admin, actual);
        assertEquals(admin.getRole(), actual.getRole());
    }

    @Test
    void delete() {
        Role admin = Role.builder()
                .role("admin")
                .build();

        session.save(admin);
        session.flush();
        session.clear();

        session.delete(admin);
        session.flush();
        session.clear();

        Role role = session.get(Role.class, admin.getId());

        assertNull(role);
    }
}
