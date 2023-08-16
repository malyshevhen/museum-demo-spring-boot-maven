package com.example.services.museum.exceptions;

/**
 * Exception class to indicate that an author with a specific ID was not found.
 *
 * @author Evhen Malysh
 */
public class AuthorNotFoundException extends RuntimeException {

    /**
     * Constructs an instance of {@code AuthorNotFoundException}
     * with the specified detail message.
     *
     * @param message The detail message (which is saved for
     *                later retrieval by the {@link #getMessage()} method).
     */
    public AuthorNotFoundException(final String message) {
        super(message);
    }
}
