package com.example.gallery.repositories;

import com.example.gallery.domain.Artwork;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    /**
     * Retrieve {@link Optional} of {@link Artwork} by it name.
     *
     * @param name unique name of the artwork
     * @return {@link Optional} of searching {@link Artwork}
     */
    Optional<Artwork> findByName(String name);
}
