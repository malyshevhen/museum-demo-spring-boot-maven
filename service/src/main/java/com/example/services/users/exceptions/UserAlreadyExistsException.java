package com.example.services.users.exceptions;

/**
 * Exception thrown when an attempt is made to create a user
 * that already exists.
 */
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new UserAlreadyExistsException
     * with the specified detail message.
     *
     * @param message The detail message describing
     *                the reason for the exception.
     */
    public UserAlreadyExistsException(final String message) {
        super(message);
    }
}
