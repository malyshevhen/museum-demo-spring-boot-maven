package com.example.domain.users;

import com.example.utils.FakeModelFactory;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.example.constants.TestConstants.EMPTY_STRING;
import static com.example.constants.TestConstants.Users.OVERSIZED_USER_FIELD;
import static com.example.constants.TestConstants.Users.UNDERSIZED_USER_FIELD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of User validations.
 *
 * @author Evhen Malysh
 */
class UserTest {
    private Validator validator;

    @BeforeEach
    void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @DisplayName("No constraint violations with valid user")
    @Test
    void testPass() {
        var validUser = FakeModelFactory.getValidUser();
        var violations = validator.validate(validUser);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when firstname is blank")
    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidStringFields")
    void invalidFirstnameFails(String firstname) {
        var user = FakeModelFactory.getValidUser();
        user.setFirstName(firstname);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when lastname is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidStringFields")
    void invalidLastnameFails(String lastname) {
        var user = FakeModelFactory.getValidUser();
        user.setLastName(lastname);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidStringFields() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(UNDERSIZED_USER_FIELD),
                Arguments.of(OVERSIZED_USER_FIELD)
        );
    }

    @DisplayName("Constraint violations should be created when email is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "email", "email@", "@email", "email@gmail", "@gmail.com"})
    void invalidEmailFails(String email) {
        var user = FakeModelFactory.getValidUser();
        user.setEmail(email);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when password is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "noDigits", "sh0rt"})
    void invalidPasswordFails(String password) {
        var user = FakeModelFactory.getValidUser();
        user.setPassword(password);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }
}