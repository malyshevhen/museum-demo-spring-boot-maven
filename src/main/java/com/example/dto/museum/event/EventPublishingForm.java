package com.example.dto.museum.event;

import com.example.domain.museum.Event;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.example.constraints.museum.EventConstraints.MAX_CONTENT_LENGTH;
import static com.example.constraints.museum.EventConstraints.MAX_TITLE_LENGTH;
import static com.example.constraints.museum.EventConstraints.MIN_CONTENT_LENGTH;
import static com.example.constraints.museum.EventConstraints.MIN_TITLE_LENGTH;

/**
 * DTO for {@link Event}
 */
@Validated
public record EventPublishingForm(

        @NotNull
        @NotBlank
        @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
        String title,

        @NotNull
        @NotBlank
        @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
        String content,

        @NotNull
        @Future
        LocalDateTime timing,

        @NotNull
        @Positive
        Integer capacity,

        @NotNull
        @Positive
        Long authorId

) implements Serializable {
}