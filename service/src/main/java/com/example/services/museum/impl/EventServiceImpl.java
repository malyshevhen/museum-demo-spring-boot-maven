// @formatter:off

package com.example.services.museum.impl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.museum.domain.Event;
import com.example.museum.repositories.EventRepository;
import com.example.services.museum.EventService;
import com.example.services.museum.exceptions.EventNotFoundException;

import lombok.RequiredArgsConstructor;

/**
 * Service class for managing museum events.
 *
 * @author Evhen Malysh
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    // @formatter:on

    /**
     * JPA repository for managing Event entities in DB.
     */
    private final EventRepository eventRepository;

    /**
     * Get a list of all events.
     *
     * @return List of events.
     */
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    /**
     * Get a specific event by its ID.
     *
     * @param id The ID of the event.
     * @return The event with the given ID.
     * @throws EventNotFoundException if the event with the given ID
     *                                is not found.
     */
    public Event getById(final Long id) {
        return eventRepository.findById(id)
                .orElseThrow(throwNotFoundException(id));
    }

    /**
     * Create a new event.
     *
     * @param event The event to create.
     * @return The created event.
     * @throws IllegalArgumentException if the event is null.
     */
    @Transactional
    public Event save(final Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        return eventRepository.save(event);
    }

    /**
     * Delete an event by its ID.
     *
     * @param id The ID of the event to delete.
     * @throws IllegalArgumentException if the event is not found.
     */
    @Transactional
    public void deleteById(final Long id) {
        var event = getById(id);
        eventRepository.delete(event);
    }

    private Supplier<EventNotFoundException> throwNotFoundException(
            final Long id) {
        return () -> new EventNotFoundException(
                String.format("Event not found with ID: %s", id));
    }
}
