package com.example.dto.users;

import com.example.domain.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Validated
public record UserResponse(
        @NotNull @Positive Long id,
        @NotNull @NotBlank String firstName,
        @NotNull @NotBlank String lastName,
        @NotNull @NotBlank String email
) implements Serializable {
}