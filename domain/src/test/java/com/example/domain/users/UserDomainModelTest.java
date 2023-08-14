package com.example.domain.users;

import com.example.domain.config.AbstractDomainModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.example.constants.TestConstants.EMPTY_STRING;
import static com.example.constants.TestConstants.Users.OVERSIZED_NAME_FIELD;
import static com.example.constants.TestConstants.Users.UNDERSIZED_NAME_FIELD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of User validations.
 *
 * @author Evhen Malysh
 */
class UserDomainModelTest extends AbstractDomainModelTest {

    @DisplayName("No constraint violations with valid user")
    @Test
    void testPass() {
        var validUser = getValidUser();
        var violations = validate(validUser);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when firstname is blank")
    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidStringFields")
    void invalidFirstnameFails(String firstname) {
        var user = getValidUser();
        user.setFirstName(firstname);

        var violations = validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when lastname is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource("invalidStringFields")
    void invalidLastnameFails(String lastname) {
        var user = getValidUser();
        user.setLastName(lastname);

        var violations = validate(user);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidStringFields() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(UNDERSIZED_NAME_FIELD),
                Arguments.of(OVERSIZED_NAME_FIELD)
        );
    }

    @DisplayName("Constraint violations should be created when email is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "email", "email@", "@email", "email@gmail", "@gmail.com"})
    void invalidEmailFails(String email) {
        var user = getValidUser();
        user.setEmail(email);

        var violations = validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when password is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "noDigits", "sh0rt"})
    void invalidPasswordFails(String password) {
        var user = getValidUser();
        user.setPassword(password);

        var violations = validate(user);

        assertFalse(violations.isEmpty());
    }
}