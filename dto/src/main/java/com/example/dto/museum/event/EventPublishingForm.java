package com.example.dto.museum.event;

import com.example.domain.museum.Event;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Event}
 */
@Validated
public record EventPublishingForm(
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String body,
        @NotNull LocalDateTime timing,
        @NotNull @Positive Integer capacity,
        @NotNull Event.EventStatus status,
        Long authorId
) implements Serializable {
}