package com.example.web.museum.controllers;

import com.example.dto.museum.event.EventPublishingForm;
import com.example.dto.museum.event.EventWithContent;
import com.example.dto.museum.event.EventWithoutContent;
import com.example.services.museum.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing museum events.
 */
@RestController
@RequestMapping("/api/museum/events")
@Validated
@Tag(name = "Events", description = "API operations related to museum events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    /**
     * Get a list of all events.
     *
     * @return List of events.
     */
    @GetMapping
    @Operation(summary = "Get a list of all events")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of events",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventWithoutContent.class)))})
    @ResponseStatus(code = HttpStatus.OK)
    public List<EventWithoutContent> getAll() {
        return eventService.getAll();
    }

    /**
     * Get a specific event by its ID.
     *
     * @param id The ID of the event.
     * @return The event with the given ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a specific event by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the event",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventWithContent.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")})
    @ResponseStatus(HttpStatus.OK)
    public EventWithContent getById(@PathVariable @NotNull @Positive final Long id) {
        return eventService.getById(id);
    }

    /**
     * Create a new event.
     *
     * @param eventPublishingForm The event to create.
     * @return The created event.
     */
    @PostMapping
    @Operation(summary = "Create a new event")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Event created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EventWithContent.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Event cant be null")})
    @ResponseStatus(HttpStatus.CREATED)
    public EventWithContent create(
            @RequestBody @NotNull final EventPublishingForm eventPublishingForm) {
        return eventService.save(eventPublishingForm);
    }

    /**
     * Delete an event by its ID.
     *
     * @param id The ID of the event to delete.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an event by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Event deleted successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Event not found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive final Long id) {
        eventService.deleteById(id);
    }
}
