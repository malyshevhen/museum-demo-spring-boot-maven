package com.example.dto.users;

import com.example.domain.users.User;
import jakarta.validation.constraints.*;

import java.io.Serializable;

import static com.example.constraints.users.UserConstraints.*;

/**
 * DTO for {@link User}
 */
public record UserRegistrationForm(

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String firstName,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String lastName,

        @NotNull
        @NotBlank
        @Pattern(regexp = EMAIL_REGEXP)
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String email,

        @NotNull
        @Size(max = MAX_NAME_LENGTH)
        @Pattern(regexp = PASSWORD_REGEXP)
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String password,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String addressCity,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String addressStreet,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String addressNumber,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String addressApartment,

        @NotNull
        @NotBlank
        @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
        String addressZip

) implements Serializable {
}