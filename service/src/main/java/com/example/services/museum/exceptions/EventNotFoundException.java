package com.example.services.museum.exceptions;

/**
 * Exception class to indicate that an event with a specific ID was not found.
 *
 * @author Evhen Malysh
 */
public class EventNotFoundException extends RuntimeException {

    /**
     * Constructs an instance of {@code EventNotFoundException}
     * with the specified detail message.
     *
     * @param message The detail message
     *                (which is saved for later retrieval by the
     *                {@link #getMessage()} method).
     */
    public EventNotFoundException(final String message) {
        super(message);
    }
}
