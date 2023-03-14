package com.cpallas.entities.dao;

import com.cpallas.entities.BaseTest;
import com.cpallas.util.TestDataImporter;
import org.junit.jupiter.api.BeforeAll;

public class BaseDaoTest extends BaseTest {

    @BeforeAll
    public static void beforeAll() {
        TestDataImporter.importDate(sessionFactory);
    }

}
