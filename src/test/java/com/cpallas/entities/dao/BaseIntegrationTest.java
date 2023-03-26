package com.cpallas.entities.dao;

import com.cpallas.config.ApplicationConfiguration;
import com.cpallas.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

public abstract class BaseIntegrationTest {

    protected static SessionFactory sessionFactory;
    protected static EntityManager entityManager;
    private static AnnotationConfigApplicationContext context;

    @BeforeAll
    static void beforeAll() {
        context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        sessionFactory = context.getBean(SessionFactory.class);
        entityManager = (EntityManager) Proxy.newProxyInstance(SessionFactory.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
    }

    @BeforeEach
    void beforeEach() {
        entityManager.getTransaction().begin();
        TestDataImporter.importData(entityManager);
    }

    @AfterEach
    void afterEach() {
        entityManager.getTransaction().rollback();
    }

    @AfterAll
    static void afterAll() {
        context.close();
    }
}
