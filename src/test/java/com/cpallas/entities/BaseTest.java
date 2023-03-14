package com.cpallas.entities;

import com.cpallas.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    protected static final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    protected static Session session;

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
