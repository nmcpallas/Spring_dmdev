package com.cpallas.entities.crud;

import com.cpallas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    private static SessionFactory sessionFactory;
    protected static Session session;

    @BeforeAll
    public static void beforeAll() {
        sessionFactory = HibernateUtil.buildSessionFactory();
    }

    @AfterAll
    public static void afterAll() {
        sessionFactory.close();
    }

    @BeforeEach
    public void beforeEach() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    public void afterEach() {
        session.getTransaction().rollback();
        session.close();
    }
}
