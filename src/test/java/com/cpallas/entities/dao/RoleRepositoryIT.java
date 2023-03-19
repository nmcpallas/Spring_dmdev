package com.cpallas.entities.dao;

import com.cpallas.dao.RoleRepository;
import com.cpallas.entities.Role;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleRepositoryIT extends BaseIntegrationTest {

    private final RoleRepository roleRepository = new RoleRepository(session);

    @Test
    public void findAll() {
        List<Role> roles = roleRepository.findAll();

        assertEquals(2, roles.size());
        assertTrue(roles.stream().map(Role::getRole).toList().containsAll(List.of("admin", "user")));
    }
}
