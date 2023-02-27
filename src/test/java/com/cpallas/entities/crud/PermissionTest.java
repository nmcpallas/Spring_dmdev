package com.cpallas.entities.crud;

import com.cpallas.entities.Permission;
import com.cpallas.entities.Role;
import com.cpallas.entities.User;
import com.cpallas.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PermissionTest {

    @Test
    void create() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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

        Permission actualPermission = session.get(Permission.class, 1);

        assertEquals(permission, actualPermission);

        session.getTransaction().commit();
    }

    @Test
    void update() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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

        Permission actualPermission = session.get(Permission.class, 1);

        assertEquals(permission, actualPermission);

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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

        Permission actualPermission = session.get(Permission.class, 1);

        assertNull(actualPermission);

        session.getTransaction().commit();
    }
}
