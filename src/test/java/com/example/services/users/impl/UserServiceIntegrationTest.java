package com.example.services.users.impl;

import com.example.config.AbstractServiceIntegrationTest;
import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserResponse;
import com.example.services.users.UserService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceIntegrationTest extends AbstractServiceIntegrationTest<UserRegistrationForm> {

    @Autowired
    private UserService userService;

    @AfterEach
    void tearDown() {
        clearAdditionalSettings();
    }

    @Test
    void getAll() {
        var users = userService.getAll();

        users.stream()
                .map(UserResponse::id)
                .forEach(Assertions::assertNotNull);

        users.stream()
                .map(UserResponse::email)
                .forEach(Assertions::assertNotNull);

        users.stream()
                .map(UserResponse::firstName)
                .forEach(Assertions::assertNotNull);

        users.stream()
                .map(UserResponse::lastName)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void getById() {
        var user = userService.getById(1L);

        assertNotNull(user.id());
        assertNotNull(user.firstName());
        assertNotNull(user.lastName());
        assertNotNull(user.email());
    }

    @Test
    void saveShouldPass() {
        set("password", "ValidPassword1");
        set("email", "valid@gmail.com");

        var registrationForm = getModel();
        var userShortResponse = userService.save(registrationForm);

        assertNotNull(userShortResponse.id());
        assertNotNull(userShortResponse.email());
        assertNotNull(userShortResponse.email());
        assertNotNull(userShortResponse.firstName());
        assertNotNull(userShortResponse.lastName());
    }

    @Test
    void saveShouldThrowExceptionWhenFormInvalid() {
        var registrationForm = getModel();

        assertThrows(ConstraintViolationException.class,
                () -> userService.save(registrationForm));
    }
}