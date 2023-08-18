package com.example.dto.museum.author;

import com.example.config.AbstractInstancioTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorRegistrationFormInstancioTest extends AbstractInstancioTest<AuthorRegistrationForm> {

    @AfterEach
    void tearDown() {
        clearAdditionalSettings();
    }

    @Test
    void shouldPass() {
        var authorRegistrationForm = getModel();
        var violations = validate(authorRegistrationForm);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void invalidUsername(String value) {
        set("username", value);
        var user = getModel();
        var validate = validate(user);
        assertFalse(validate.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {-100L, 0L})
    void invalidUserId(Long value) {
        set("userId", value);
        var userShortResponse = getModel();
        var violations = validate(userShortResponse);
        assertFalse(violations.isEmpty());
    }
}