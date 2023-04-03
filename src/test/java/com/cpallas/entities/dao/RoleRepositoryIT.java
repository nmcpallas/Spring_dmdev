package com.cpallas.entities.dao;

import com.cpallas.dao.RoleRepository;
import com.cpallas.entities.Role;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class RoleRepositoryIT extends BaseIntegrationTest {

    private final RoleRepository roleRepository;

    @Test
    void findAll() {
        List<Role> roles = roleRepository.findAll();

        assertEquals(2, roles.size());
        assertTrue(roles.stream().map(Role::getRole).toList().containsAll(List.of("admin", "user")));
    }
}
