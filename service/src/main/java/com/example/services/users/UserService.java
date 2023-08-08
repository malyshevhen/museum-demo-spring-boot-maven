package com.example.services.users;

import java.util.List;

import com.example.users.domain.User;

/**
 * Service interface for handling operations related to users.
 */
public interface UserService {

    /**
     * Get a list of all users.
     *
     * @return List of User objects representing all users.
     */
    List<User> getAll();

    /**
     * Get a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object representing the requested user,
     *         or null if not found.
     */
    User getById(Long id);

    /**
     * Create a new user.
     *
     * @param user The User object containing the details of the new user.
     * @return The created User object.
     */
    User save(User user);

    /**
     * Update an existing user.
     *
     * @param id   The ID of the user to update.
     * @param user The User object containing the updated details.
     * @return The updated User object, or null if user not found.
     */
    User update(Long id, User user);

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    void deleteById(Long id);
}
