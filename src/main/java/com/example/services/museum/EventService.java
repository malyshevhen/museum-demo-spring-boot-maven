package com.example.services.museum;

import com.example.dto.museum.event.EventPublishingForm;
import com.example.dto.museum.event.EventWithContent;
import com.example.dto.museum.event.EventWithoutContent;
import com.example.services.museum.exceptions.EventNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

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
    List<EventWithoutContent> getAll();

    /**
     * Get a specific event by its ID.
     *
     * @param id The ID of the event.
     * @return The event with the given ID.
     * @throws EventNotFoundException if the event with the given
     *                                ID is not found.
     */
    EventWithContent getById(@NotNull @Positive Long id);

    /**
     * Create a new event.
     *
     * @param event The event to create.
     * @return The created event.
     * @throws IllegalArgumentException if the event is null.
     */
    EventWithContent save(@NotNull @Valid EventPublishingForm event);

    /**
     * Delete an event by its ID.
     *
     * @param id The ID of the event to delete.
     * @throws IllegalArgumentException if the event is not found.
     */
    void deleteById(@NotNull @Positive Long id);
}
