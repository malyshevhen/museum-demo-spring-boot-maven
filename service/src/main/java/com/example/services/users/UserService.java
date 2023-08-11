package com.example.services.users;

import com.example.domain.users.Address;
import com.example.domain.users.User;
import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserShortResponse;

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
    List<UserShortResponse> getAll();

    /**
     * Get a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object representing the requested user,
     * or null if not found.
     */
    UserShortResponse getById(Long id);

    /**
     * Create a new user.
     *
     * @param user The User object containing the details of the new user.
     * @return The created User object.
     */
    UserShortResponse save(UserRegistrationForm user);

    /**
     * Update an existing user`s address.
     *
     * @param id   The ID of the user to update.
     * @param address The user`s address to update.
     * @return The updated user.
     */
    UserShortResponse updateAddress(Long id, Address address);

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    void deleteById(Long id);
}
