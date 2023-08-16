package com.example.dto.museum.author;


import com.example.domain.museum.Author;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * DTO for {@link Author}
 */
@Validated
public record AuthorRegistrationForm(
        @NotNull @NotBlank String username,
        @NotNull @Positive Long userId
) implements Serializable {
}