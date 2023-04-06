package com.cpallas.entities.dao;

import com.cpallas.annotation.IT;
import com.cpallas.util.TestDataImporter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

@IT
public abstract class BaseIntegrationTest {

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach() {
        TestDataImporter.importData(entityManager);
    }
}
