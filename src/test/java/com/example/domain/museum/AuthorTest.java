package com.example.domain.museum;

import com.example.constants.TestConstants;
import com.example.config.AbstractInstancioDomainTest;
import com.example.domain.users.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static com.example.constants.TestConstants.EMPTY_STRING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of Author validations.
 *
 * @author Evhen Malysh
 */
class AuthorTest extends AbstractInstancioDomainTest {

    @DisplayName("No constraint violations with valid author")
    @Test
    void testPass() {
        var author = getEntity(Author.class);
        var violations = validate(author);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when username is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource
    void invalidUsername(String username) {
        var author = getEntity(Author.class);
        author.setUsername(username);

        var violations = validate(author);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidUsername() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(TestConstants.Authors.UNDERSIZED_USERNAME_FIELD),
                Arguments.of(TestConstants.Authors.OVERSIZED_USERNAME_FIELD));
    }

    @DisplayName("Constraint violations should be created when user is invalid")
    @ParameterizedTest
    @NullSource
    @MethodSource
    void invalidUser(User user) {
        var author = getEntity(Author.class);
        author.setUser(user);

        var violations = validate(author);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidUser() {
        var user = getEntity(User.class);
        user.setEmail("");
        return Stream.of(Arguments.of(user));
    }
}
