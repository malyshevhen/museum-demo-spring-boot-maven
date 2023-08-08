package com.example.services.gallery.exceptions;

import com.example.gallery.domain.Artwork;

/**
 * Exception that should be thrown when {@link Artwork}
 * is already exists.
 *
 * @author Evhen Malysh
 */
public class ArtworkAlreadyExistException extends RuntimeException {

    /**
     * @param message message that explains the error
     */
    public ArtworkAlreadyExistException(final String message) {
        super(message);
    }
}
