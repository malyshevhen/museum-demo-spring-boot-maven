package com.example.domain.museum;

import com.example.constants.TestConstants;
import com.example.domain.users.User;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static com.example.constants.TestConstants.*;
import static com.example.utils.InstancioDomainModels.getAuthorModel;
import static com.example.utils.InstancioDomainModels.getUserModel;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of Author validations.
 *
 * @author Evhen Malysh
 */
@ExtendWith(InstancioExtension.class)
class AuthorTest {
    private Validator validator;

    @WithSettings
    private final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true);

    @BeforeEach
    void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @DisplayName("No constraint violations with valid author")
    @Test
    void testPass() {
        var author = Instancio.of(getAuthorModel()).create();
        var violations = validator.validate(author);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when username is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @MethodSource
    void invalidUsername(String username) {
        var author = Instancio.of(getAuthorModel()).create();
        author.setUsername(username);

        var violations = validator.validate(author);

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
        var author = Instancio.of(getAuthorModel()).create();
        author.setUser(user);

        var violations = validator.validate(author);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidUser() {
        return Stream.of(
                Arguments.of(
                        Instancio.of(getUserModel())
                                .set(field("email"), EMPTY_STRING)
                                .create())
        );
    }
}
