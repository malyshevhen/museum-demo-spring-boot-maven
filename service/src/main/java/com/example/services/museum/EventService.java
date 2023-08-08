package com.example.services.museum;

import java.util.List;

import com.example.museum.domain.Event;

/**
 * Service interface for managing museum events.
 *
 * @author Evhen Malysh
 */
public interface EventService {

    /**
     * Get a list of all events.
     *
     * @return List of events.
     */
    List<Event> getAll();

    /**
     * Get a specific event by its ID.
     *
     * @param id    The ID of the event.
     * @return      The event with the given ID.
     * @throws      EventNotFoundException if the event with the given
     *              ID is not found.
     */
    Event getById(Long id);

    /**
     * Create a new event.
     *
     * @param event The event to create.
     * @return The created event.
     * @throws IllegalArgumentException if the event is null.
     */
    Event save(Event event);

    /**
     * Delete an event by its ID.
     *
     * @param id The ID of the event to delete.
     * @throws IllegalArgumentException if the event is not found.
     */
    void deleteById(Long id);
}
