package com.example.services.museum.impl;

import com.example.domain.museum.Event;
import com.example.dto.museum.event.EventPublishingForm;
import com.example.dto.museum.event.EventWithContent;
import com.example.dto.museum.event.EventWithoutContent;
import com.example.repositories.museum.AuthorRepository;
import com.example.repositories.museum.EventRepository;
import com.example.services.museum.EventService;
import com.example.services.museum.exceptions.AuthorNotFoundException;
import com.example.services.museum.exceptions.EventNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.function.Supplier;

/**
 * Service class for managing museum events.
 *
 * @author Evhen Malysh
 */
@Service
@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    public static final String EVENT_NOT_FOUND_WITH_ID = "Event not found with ID: %s";
    private final EventRepository eventRepository;
    private final AuthorRepository authorRepository;

    private static Supplier<EventNotFoundException> getEventNotFoundExceptionSupplier(Long id) {
        return () -> new EventNotFoundException(
                String.format(EVENT_NOT_FOUND_WITH_ID, id));
    }

    /**
     * Get a list of all events.
     *
     * @return List of events.
     */
    @Override
    public List<EventWithoutContent> getAll() {
        return eventRepository.findAllEventsWithoutContent();
    }

    /**
     * Get a specific event by its ID.
     *
     * @param id The ID of the event.
     * @return The event with the given ID.
     * @throws EventNotFoundException if the event with the given ID
     *                                is not found.
     */
    @Override
    public EventWithContent getById(@NotNull @Positive final Long id) {
        return eventRepository.findEventWithContentById(id)
                .orElseThrow(getEventNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new event.
     *
     * @param form Form of the event to create.
     * @return The created event.
     * @throws IllegalArgumentException if the event is null or invalid.
     * @throws AuthorNotFoundException  if the author is not found.
     */
    @Override
    @Transactional
    public EventWithContent save(@NotNull @Valid final EventPublishingForm form) {
        var authorId = form.authorId();
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(
                        String.format(AuthorServiceImpl.AUTHOR_WITH_ID_NOT_FOUND, authorId)));
        var event = new Event(
                form.title(),
                form.content(),
                form.timing(),
                form.capacity(),
                author
        );
        var id = eventRepository.save(event).getId();
        return getById(id);
    }

    /**
     * Delete an event by its ID.
     *
     * @param id The ID of the event to delete.
     * @throws IllegalArgumentException if the event is not found.
     */
    @Override
    @Transactional
    public void deleteById(final Long id) {
        if (isNotPresent(id)) {
            throw new EventNotFoundException(
                    String.format(EVENT_NOT_FOUND_WITH_ID, id));
        }
        eventRepository.deleteById(id);
    }

    private boolean isNotPresent(Long id) {
        return !eventRepository.existsById(id);
    }
}
