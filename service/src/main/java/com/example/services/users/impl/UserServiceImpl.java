package com.example.services.users.impl;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.services.users.UserService;
import com.example.services.users.exceptions.UserAlreadyExistsException;
import com.example.services.users.exceptions.UserNotFoundException;
import com.example.dao.users.domain.User;
import com.example.dao.users.repositories.UserRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the UserService interface.
 *
 * @author Evhen Malysh
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     *
     */
    private final UserRepository userRepository;

    /**
     * Get a list of all users.
     *
     * @return List of User objects representing all users.
     */
    @Override
    public List<User> getAll() {
        var allUsers = userRepository.findAll();
        if (allUsers.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return allUsers;
    }

    /**
     * Get a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The User object representing the requested user,
     *         or null if not found.
     */
    @Override
    public User getById(@NotNull @Positive final Long id) {
        return userRepository.findById(id)
                .orElseThrow(getUserNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new user.
     *
     * @param user The User object containing the details of the new user.
     * @return The created User object.
     */
    @Override
    public User save(@NotNull @Valid final User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(getUserAlreadyExistsExceptionConsumer());
        return userRepository.save(user);
    }

    /**
     * Update an existing user.
     *
     * @param id   The ID of the user to update.
     * @param user The User object containing the updated details.
     * @return The updated User object, or null if user not found.
     */
    @Override
    public User update(
            @NotNull @Positive final Long id,
            @NotNull @Valid final User user) {
        var existingUser = getById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setAddress(user.getAddress());

        return userRepository.save(existingUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    @Override
    public void deleteById(@NotNull @Positive final Long id) {
        userRepository.deleteById(id);
    }

    private static Supplier<UserNotFoundException> getUserNotFoundExceptionSupplier(Long id) {
        return () -> new UserNotFoundException(
                String.format("User with ID: %s not found", id));
    }

    private static Consumer<? super User> getUserAlreadyExistsExceptionConsumer() {
        return u -> {
            throw new UserAlreadyExistsException("User already exists");
        };
    }
}
