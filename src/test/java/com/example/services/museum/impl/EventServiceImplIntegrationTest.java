package com.example.services.museum.impl;

import com.example.config.AbstractServiceIntegrationTest;
import com.example.dto.museum.event.EventPublishingForm;
import com.example.dto.museum.event.EventWithoutContent;
import com.example.services.museum.EventService;
import com.example.services.museum.exceptions.EventNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventServiceImplIntegrationTest extends AbstractServiceIntegrationTest<EventPublishingForm> {

    @Autowired
    private EventService eventService;

    @AfterEach
    void tearDown() {
        clearAdditionalSettings();
    }

    @Test
    void getAll() {
        var eventsWithoutContent = eventService.getAll();

        eventsWithoutContent.stream()
                .map(EventWithoutContent::id)
                .forEach(Assertions::assertNotNull);

        eventsWithoutContent.stream()
                .map(EventWithoutContent::title)
                .forEach(Assertions::assertNotNull);

        eventsWithoutContent.stream()
                .map(EventWithoutContent::status)
                .forEach(Assertions::assertNotNull);

        eventsWithoutContent.stream()
                .map(EventWithoutContent::capacity)
                .forEach(Assertions::assertNotNull);

        eventsWithoutContent.stream()
                .map(EventWithoutContent::authorId)
                .forEach(Assertions::assertNotNull);

        eventsWithoutContent.stream()
                .map(EventWithoutContent::authorUsername)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void getById() {
        long id = 1L;
        var eventWithContent = eventService.getById(id);

        assertNotNull(eventWithContent.id());
        assertNotNull(eventWithContent.title());
        assertNotNull(eventWithContent.status());
        assertNotNull(eventWithContent.content());
        assertNotNull(eventWithContent.capacity());
        assertNotNull(eventWithContent.authorId());
        assertNotNull(eventWithContent.authorUsername());
    }

    @Test
    void save() {
        long authorId = 1L;
        set("authorId", authorId);
        var eventToSave = getModel();

        var eventWithContent = eventService.save(eventToSave);

        assertNotNull(eventWithContent.id());
        assertNotNull(eventWithContent.title());
        assertNotNull(eventWithContent.status());
        assertNotNull(eventWithContent.content());
        assertNotNull(eventWithContent.capacity());
        assertNotNull(eventWithContent.authorId());
        assertNotNull(eventWithContent.authorUsername());
    }

    @Test
    void deleteById() {
        long id = 1L;
        eventService.deleteById(id);

        assertThrows(EventNotFoundException.class, () -> eventService.getById(id));
    }
}