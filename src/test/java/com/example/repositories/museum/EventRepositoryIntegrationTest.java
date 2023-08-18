package com.example.repositories.museum;

import com.example.config.AbstractRepositoryIntegrationTest;
import com.example.dto.museum.event.EventWithContent;
import com.example.dto.museum.event.EventWithoutContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EventRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void findAllEventsWithoutContent() {
        var events = eventRepository.findAllEventsWithoutContent();

        events.stream()
                .map(EventWithoutContent::id)
                .forEach(Assertions::assertNotNull);

        events.stream()
                .map(EventWithoutContent::title)
                .forEach(Assertions::assertNotNull);

        events.stream()
                .map(EventWithoutContent::status)
                .forEach(Assertions::assertNotNull);

        events.stream()
                .map(EventWithoutContent::capacity)
                .forEach(Assertions::assertNotNull);

        events.stream()
                .map(EventWithoutContent::timing)
                .forEach(Assertions::assertNotNull);

        events.stream()
                .map(EventWithoutContent::authorId)
                .forEach(Assertions::assertNotNull);

        events.stream()
                .map(EventWithoutContent::authorUsername)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void findEventWithContentById() {
        var event = eventRepository.findEventWithContentById(1L);

        event.map(EventWithContent::id)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::title)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::content)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::timing)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::capacity)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::status)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::authorId)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);

        event.map(EventWithContent::authorUsername)
                .ifPresentOrElse(Assertions::assertNotNull, Assertions::fail);
    }
}