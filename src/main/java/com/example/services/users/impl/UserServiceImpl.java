package com.example.services.users.impl;

import com.example.domain.users.Address;
import com.example.domain.users.User;
import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserShortResponse;
import com.example.repositories.users.UserRepository;
import com.example.services.users.UserService;
import com.example.services.users.exceptions.UserAlreadyExistsException;
import com.example.services.users.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of the UserService interface.
 *
 * @author Evhen Malysh
 */
@Service
@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    public static final String USER_WITH_EMAIL_ALREADY_EXIST = "User with email: %s already exist.";
    public static final String USER_WITH_ID_NOT_FOUND = "User with ID: %d not found";
    /**
     *
     */
    private final UserRepository userRepository;

    private static Supplier<UserNotFoundException> getUserNotFoundExceptionSupplier(Long id) {
        return () -> new UserNotFoundException(
                String.format(USER_WITH_ID_NOT_FOUND, id));
    }

    /**
     * Get a list of all users.
     *
     * @return List of User objects representing all users.
     */
    @Override
    public List<UserShortResponse> getAll() {
        var allUsers = userRepository.findAllDtos();
        if (allUsers.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return allUsers;
    }

    /**
     * Get a specific user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return UserShortResponse representing the requested user.
     */
    @Override
    public UserShortResponse getById(final Long id) {
        return userRepository.findDtoById(id)
                .orElseThrow(getUserNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new user.
     *
     * @param registrationForm The form containing the details of the new user.
     * @return The created user.
     */
    @Override
    @Transactional
    public UserShortResponse save(final UserRegistrationForm registrationForm) {
        var email = registrationForm.email();
        if (isPresent(email)) {
            throw new UserAlreadyExistsException(
                    String.format(USER_WITH_EMAIL_ALREADY_EXIST, email));
        }
        var userAddress = new Address(
                registrationForm.addressCity(),
                registrationForm.addressStreet(),
                registrationForm.addressNumber(),
                registrationForm.addressApartment(),
                registrationForm.addressZip()
        );
        var userToSave = new User(
                registrationForm.firstName(),
                registrationForm.lastName(),
                registrationForm.email(),
                registrationForm.password(),
                userAddress
        );
        var savedUserId = userRepository.save(userToSave).getId();
        return getById(savedUserId);
    }

    /**
     * Update an existing user`s address.
     *
     * @param id      The ID of the user to update.
     * @param address The user`s address to update.
     * @return The updated user.
     */
    @Override
    @Transactional
    public UserShortResponse updateAddress(final Long id, final Address address) {
        var existingUser = userRepository.findById(id)
                .orElseThrow(getUserNotFoundExceptionSupplier(id));
        existingUser.setAddress(address);
        userRepository.save(existingUser);
        return getById(id);
    }

    /**
     * Delete a user by ID.
     *
     * @param id The ID of the user to delete.
     */
    @Override
    @Transactional
    public void deleteById(final Long id) {
        userRepository.deleteById(id);
    }

    private boolean isPresent(String email) {
        return userRepository.existsByEmail(email);
    }
}
