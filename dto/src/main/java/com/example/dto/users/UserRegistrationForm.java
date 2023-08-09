package com.example.dto.users;

import com.example.domain.users.User;
import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
public record UserRegistrationForm(@NotNull @NotBlank String firstName,
                                   @NotNull @NotBlank String lastName,
                                   @NotNull @Email @NotBlank String email,
                                   @NotNull @Size(max = 25) @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$") String password,
                                   @NotNull @NotBlank String addressCity,
                                   @NotNull @NotBlank String addressStreet,
                                   @NotNull @NotBlank String addressNumber,
                                   @NotNull @NotBlank String addressApartment,
                                   @NotNull @NotBlank String addressZip) implements Serializable {
}