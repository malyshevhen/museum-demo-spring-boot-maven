package com.example.services.users.exceptions;

/**
 * Exception thrown when a user is not found.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message The detail message describing
     *                the reason for the exception.
     */
    public UserNotFoundException(final String message) {
        super(message);
    }
}
