package com.example.services.museum.exceptions;

/**
 * Exception class to indicate that an author with a specific ID already exists.
 *
 * @author Evhen Malysh
 */
public class AuthorAlreadyExistException extends RuntimeException {

    /**
     * Constructs an instance of {@code AuthorAlreadyExistException}
     * with the specified detail message.
     *
     * @param message The detail message (which is saved for
     *                later retrieval by the {@link #getMessage()} method).
     */
    public AuthorAlreadyExistException(final String message) {
        super(message);
    }
}
