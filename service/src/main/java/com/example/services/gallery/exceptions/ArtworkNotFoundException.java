package com.example.services.gallery.exceptions;

import com.example.gallery.domain.Artwork;

/**
 * Exception that should be thrown when {@link Artwork}
 * is not found.
 *
 * @author Evhen Malysh
 */
public class ArtworkNotFoundException extends RuntimeException {

    /**
     * @param message message that explains the error
     */
    public ArtworkNotFoundException(final String message) {
        super(message);
    }
}
