package com.example.repositories.users;

import com.example.config.AbstractRepositoryIntegrationTest;
import com.example.domain.users.User;
import com.example.dto.users.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllDtosShouldPass() {
        var users = userRepository.findAllDtos();

        assertFalse(users.isEmpty());
        users.stream()
                .map(UserResponse::id)
                .forEach(Assertions::assertNotNull);

    }

    @Test
    void findDtoByIdShouldPass() {
        userRepository.findDtoById(1L)
                .ifPresentOrElse(
                        u -> assertNotNull(u.id()),
                        Assertions::fail
                );
    }

    @Test
    void existByEmailShouldPass() {
        assertTrue(userRepository.existsByEmail("john@example.com"));
        assertFalse(userRepository.existsByEmail("NotExisting@email.com"));
    }

    @Test
    void saveShouldPass() {
        var user = getEntity(User.class);

        var optionalSavedUser = userRepository.save(user);
        assertNotNull(optionalSavedUser.getId());
    }
}