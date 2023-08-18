package com.example.domain.museum;

import com.example.constants.TestConstants;
import com.example.config.AbstractInstancioDomainTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static com.example.constants.TestConstants.EMPTY_STRING;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of Event validations.
 *
 * @author Evhen Malysh
 */
class EventTest extends AbstractInstancioDomainTest {

    @DisplayName("No constraint violations with valid event")
    @Test
    void testPass() {
        var event = getEntity(Event.class);
        var violations = validate(event);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when title is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidTitle(String title) {
        var event = getEntity(Event.class);
        event.setTitle(title);

        var violations = validate(event);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidTitle() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(TestConstants.Events.UNDERSIZED_TITLE_FIELD),
                Arguments.of(TestConstants.Events.OVERSIZED_TITLE_FIELD));
    }

    @DisplayName("Constraint violations should be created when body is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidContent(String body) {
        var event = getEntity(Event.class);
        event.setContent(body);

        var violations = validate(event);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidContent() {
        return Stream.of(
                Arguments.of(EMPTY_STRING),
                Arguments.of(TestConstants.Events.UNDERSIZED_CONTENT_FIELD),
                Arguments.of(TestConstants.Events.OVERSIZED_CONTENT_FIELD));
    }


    @DisplayName("Constraint violations should be created when author is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidAuthor(Author author) {
        var event = getEntity(Event.class);
        event.setAuthor(author);

        var violations = validate(event);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidAuthor() {
        var author = getEntity(Author.class);
        author.setUsername("");
        return Stream.of(Arguments.of(author));
    }

    @ParameterizedTest
    @MethodSource
    void invalidTiming(LocalDateTime timing) {
        var event = getEntity(Event.class);
        event.setTiming(timing);

        var violations = validate(event);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidTiming() {
        return Stream.of(
                Arguments.of(LocalDateTime.now()),
                Arguments.of(LocalDateTime.MIN)
        );
    }
}