package com.example.config;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(properties = "spring.datasource.url=jdbc:tc:postgresql:15.2-alpine:///museum_db",
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractServiceIntegrationTest<T> extends AbstractInstancioTest<T> {
}
