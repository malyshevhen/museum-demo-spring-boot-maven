package com.example.dto.museum.author;

import com.example.dto.config.AbstractDtoTest;
import org.instancio.junit.InstancioSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorRegistrationFormDtoTest extends AbstractDtoTest {

    @ParameterizedTest
    @InstancioSource
    void shouldPass(AuthorRegistrationForm userResponse) {
        var violations = validate(userResponse);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void invalidTextFields(String value) {
        var user = getAuthorRegistrationForm(value);
        var validate = validate(user);
        assertFalse(validate.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {-100L, 0L})
    void invalidId(Long value) {
        var userShortResponse = getAuthorRegistrationForm(value);
        var violations = validate(userShortResponse);
        assertFalse(violations.isEmpty());
    }
}