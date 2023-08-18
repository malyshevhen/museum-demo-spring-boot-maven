package com.example.services.users;

import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

/**
 * Service interface for handling operations related to users.
 */
public interface UserService {

    /**
     * Get a list of all users.
     *
     * @return List of User objects representing all users.
     */
    List<UserResponse> getAll();

    /**
     * Get a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object representing the requested user,
     * or null if not found.
     */
    UserResponse getById(@NotNull @Positive Long id);

    /**
     * Create a new user.
     *
     * @param user The User object containing the details of the new user.
     * @return The created User object.
     */
    UserResponse save(@NotNull @Valid UserRegistrationForm user);
}
