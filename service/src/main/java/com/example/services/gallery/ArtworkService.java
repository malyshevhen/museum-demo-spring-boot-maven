package com.example.services.gallery;

import java.util.List;

import com.example.gallery.domain.Artwork;

/**
 * ArtWorkService is provide business logic contract for basic CRUD operations
 * with {@link Artwork} object.
 *
 * @author Evhen Malysh
 */
public interface ArtworkService {

    /**
     * @param artWork
     * @return {@link Artwork}
     */
    Artwork save(Artwork artWork);

    /**
     * @return {@link java.util.List} of {@link Artwork}
     */
    List<Artwork> getAll();

    /**
     * @param id
     * @return {@link Artwork}
     */
    Artwork getById(Long id);

    /**
     * @param id
     */
    void deleteById(Long id);
}
