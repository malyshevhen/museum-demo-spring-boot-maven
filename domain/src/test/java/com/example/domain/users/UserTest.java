package com.example.domain.users;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.example.constraints.domain.UserConstraints.MAX_NAME_LENGTH;
import static com.example.constraints.domain.UserConstraints.MIN_NAME_LENGTH;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(InstancioExtension.class)
class UserTest {
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

    @DisplayName("No constraint violations with valid user")
    @Test
    void testPass() {
        var validUser = Instancio.of(getInstancioUserModel()).create();
        var violations = validator.validate(validUser);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when firstname is blank")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "2_", "31_____________________________"})
    void invalidFirstnameFails(String firstname) {
        var user = Instancio.of(getInstancioUserModel()).create();
        user.setFirstName(firstname);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when lastname is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void invalidLastnameFails(String lastname) {
        var user = Instancio.of(getInstancioUserModel()).create();
        user.setLastName(lastname);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when email is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "email", "email@", "@email", "email@gmail", "@gmail.com"})
    void invalidEmailFails(String email) {
        var user = Instancio.of(getInstancioUserModel()).create();
        user.setEmail(email);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when password is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "noDigits", "sh0rt"})
    void invalidPasswordFails(String password) {
        var user = Instancio.of(getInstancioUserModel()).create();
        user.setPassword(password);

        var violations = validator.validate(user);

        assertFalse(violations.isEmpty());
    }

    private static Model<User> getInstancioUserModel() {
        return Instancio.of(User.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("roles"))
                .generate(field("firstName"),
                        gen -> gen.string()
                                .minLength(MIN_NAME_LENGTH)
                                .maxLength(MAX_NAME_LENGTH)
                                .mixedCase())
                .generate(field("lastName"),
                        gen -> gen.string()
                                .minLength(MIN_NAME_LENGTH)
                                .maxLength(MAX_NAME_LENGTH)
                                .mixedCase())
                .set(field("password"), "ValidPassword1")
                .set(field("email"), "valid@gmail.com")
                .toModel();
    }
}