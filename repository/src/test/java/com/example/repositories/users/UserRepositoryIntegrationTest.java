package com.example.repositories.users;

import com.example.repositories.users.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@DataJpaTest(properties = "spring.datasource.url=jdbc:tc:postgresql:12:///ums_db")
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryIntegrationTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    void findByEmailPass() {
//        var user = userRepository.findByEmail("john@example.com")
//                .orElseThrow();
//        assertNotNull(user.getId());
//    }
}