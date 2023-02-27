package com.cpallas.entities.crud;

import com.cpallas.entities.Role;
import com.cpallas.util.HibernateUtil;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
public class RoleTest {

    @Test
    void createAndRead() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Role admin = Role.builder()
                .role("admin")
                .build();

        session.save(admin);
        session.flush();
        session.clear();

        Role role = session.get(Role.class, 1);

        assertEquals(admin, role);
        assertEquals(admin.getRole(), role.getRole());

        session.getTransaction().commit();
    }

    @Test
    void update() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

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
        Role actual = session.get(Role.class, 1);

        assertEquals(admin, actual);
        assertEquals(admin.getRole(), actual.getRole());

        session.getTransaction().commit();
    }

    @Test
    void delete() {
        @Cleanup SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        Role admin = Role.builder()
                .role("admin")
                .build();

        session.save(admin);
        session.flush();
        session.clear();

        session.delete(admin);
        session.flush();
        session.clear();

        Role role = session.get(Role.class, 1);

        assertNull(role);

        session.getTransaction().commit();
    }
}
