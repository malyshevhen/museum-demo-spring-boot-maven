package com.example.dto.museum.event;

import com.example.config.AbstractInstancioTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventPublishingFormTest extends AbstractInstancioTest<EventPublishingForm> {

    @AfterEach
    void tearDown() {
        clearAdditionalSettings();
    }

    @Test
    void shouldPass() {
        var eventPublishingForm = getModel();
        var violations = validate(eventPublishingForm);
        if(!violations.isEmpty()) violations.forEach(System.out::println);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void invalidTextFields(String value) {
        var fields = Stream.of("title", "content").toList();
        fields.stream()
                .peek(field -> set(field, value))
                .map(field -> getModel())
                .map(this::validate)
                .map(Set::isEmpty)
                .forEach(Assertions::assertFalse);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {-100, 0})
    void invalidCapacity(Integer value) {
        set("capacity", value);
        var eventPublishingForm = getModel();
        var violations = validate(eventPublishingForm);
        assertFalse(violations.isEmpty());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {-100, 0})
    void invalidAuthorId(Long value) {
        set("authorId", value);
        var eventPublishingForm = getModel();
        var violations = validate(eventPublishingForm);
        assertFalse(violations.isEmpty());
    }
}