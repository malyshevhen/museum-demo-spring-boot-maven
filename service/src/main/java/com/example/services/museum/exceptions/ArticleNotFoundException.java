package com.example.services.museum.exceptions;

/**
 * Exception class to indicate that an article with a specific ID was not found.
 *
 * @author Evhen Malysh
 */
public class ArticleNotFoundException extends RuntimeException {

    /**
     * Constructs an instance of {@code ArticleNotFoundException}
     * with the specified detail message.
     *
     * @param message The detail message (which is saved for
     *                later retrieval by the {@link #getMessage()} method).
     */
    public ArticleNotFoundException(final String message) {
        super(message);
    }
}
