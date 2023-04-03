package com.cpallas.entities.dao;

import com.cpallas.util.HibernateUtil;
import com.cpallas.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Proxy;

public class BaseIntegrationTest {

    protected static SessionFactory sessionFactory;
    protected static Session session;

    @BeforeAll
    static void beforeAll() {
        sessionFactory = HibernateUtil.buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
        TestDataImporter.importData(sessionFactory);
    }

    @BeforeEach
    public void beforeEach() {
        session.beginTransaction();
    }

    @AfterEach
    public void afterEach() {
        session.getTransaction().rollback();
    }

    @AfterAll
    public static void afterAll() {
        sessionFactory.close();
    }
}
