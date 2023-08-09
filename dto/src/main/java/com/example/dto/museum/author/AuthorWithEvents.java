package com.example.dto.museum.author;

import com.example.domain.museum.Author;
import com.example.dto.museum.event.EventWithoutBody;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Author}
 */
public record AuthorWithEvents(@NotNull @Positive Long id,
                               @NotNull @NotBlank String username,
                               @NotNull @NotBlank Long userId,
                               @NotNull @NotBlank String userFirstName,
                               @NotNull @NotBlank String userLastName,
                               Set<EventWithoutBody> events) implements Serializable {
}