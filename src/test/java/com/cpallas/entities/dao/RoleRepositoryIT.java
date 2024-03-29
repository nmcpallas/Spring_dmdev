package com.cpallas.entities.dao;

import com.cpallas.repository.RoleRepository;
import com.cpallas.entities.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleRepositoryIT extends BaseIntegrationTest {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryIT(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Test
    void findAll() {
        List<Role> roles = roleRepository.findAll();

        assertEquals(2, roles.size());
        assertTrue(roles.stream().map(Role::getRole).toList().containsAll(List.of("admin", "user")));
    }
}
