package com.example.services.gallery.exceptions;

import com.example.gallery.domain.Artist;

/**
 * Exception that should be thrown when {@link Artist}
 * is not found.
 *
 * @author Evhen Malysh
 */
public class AuthorNotFoundException extends RuntimeException {

    /**
     * @param message message that explains the error
     */
    public AuthorNotFoundException(final String message) {
        super(message);
    }
}
