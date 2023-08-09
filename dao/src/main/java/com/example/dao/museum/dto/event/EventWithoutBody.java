package com.example.dao.museum.dto.event;

import com.example.dao.museum.domain.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Event}
 */
public record EventWithoutBody(@NotNull @Positive Long id,
                               @NotNull @NotBlank String title,
                               @NotNull LocalDateTime timing,
                               @NotNull @Positive Integer capacity,
                               @NotNull Event.EventStatus status, Long authorId,
                               @NotNull @NotBlank String authorUsername) implements Serializable {
}