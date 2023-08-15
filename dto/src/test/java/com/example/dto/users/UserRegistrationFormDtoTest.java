package com.example.dto.users;

import com.example.dto.config.AbstractDtoTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Stream;

import static com.example.constants.TestConstants.*;
import static com.example.constants.TestConstants.Users.OVERSIZED_NAME_FIELD;
import static com.example.constants.TestConstants.Users.UNDERSIZED_NAME_FIELD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRegistrationFormDtoTest extends AbstractDtoTest<UserRegistrationForm> {

    @BeforeEach
    void setUp() {
        setField("password", "ValidPassword1");
        setField("email", "valid@gmail.com");
    }

    @AfterEach
    void tearDown() {
        clearFields();
    }

    @DisplayName("No constraint violations with valid form")
    @Test
    void testPass() {
        var userForm = getModel();
        var violations = validate(userForm);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when firstname or lastname is invalid")
    @ParameterizedTest
    @MethodSource
    @NullAndEmptySource
    void invalidNameFields(String value) {
        var fields = Stream.of("firstName", "lastName").toList();
        fields.stream()
                .peek(field -> setField(field, value))
                .map(field -> getModel())
                .map(this::validate)
                .map(Set::isEmpty)
                .forEach(Assertions::assertFalse);
    }

    private static Stream<Arguments> invalidNameFields() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(UNDERSIZED_NAME_FIELD),
                Arguments.of(OVERSIZED_NAME_FIELD)
        );
    }

    @DisplayName("Constraint violations should be created when address is invalid")
    @ParameterizedTest
    @MethodSource
    @NullAndEmptySource
    void invalidAddressFields(String value) {
        var fields = Stream.of(
                "addressCity",
                "addressStreet",
                "addressNumber",
                "addressApartment",
                "addressZip").toList();
        fields.stream()
                .peek(field -> setField(field, value))
                .map(field -> getModel())
                .map(this::validate)
                .map(Set::isEmpty)
                .forEach(Assertions::assertFalse);
    }

    private static Stream<Arguments> invalidAddressFields() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(UNDERSIZED_FIELD),
                Arguments.of(OVERSIZED_FIELD)
        );
    }

    @DisplayName("Constraint violations should be created when email is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "email", "email@", "@email", "email@gmail", "@gmail.com"})
    void invalidEmail(String email) {
        setField("email", email);
        var userForm = getModel();

        var violations = validate(userForm);

        assertFalse(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when password is invalid")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "noDigits", "sh0rt"})
    void invalidPasswordFails(String password) {
        setField("password", password);
        var userForm = getModel();

        var violations = validate(userForm);

        assertFalse(violations.isEmpty());
    }
}