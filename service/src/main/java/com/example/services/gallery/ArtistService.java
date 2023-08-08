package com.example.services.gallery;

import java.util.List;

import com.example.gallery.domain.Artist;

/**
 * ArtWorkService is provide business logic contract for basic CRUD operations
 * with {@link Artist} object.
 *
 * @author Evhen Malysh
 */
public interface ArtistService {

    /**
     * @param artist artist to save
     * @return saved artist with id
     */
    Artist save(Artist artist);

    /**
     * @return list of all existing authors
     */
    List<Artist> getAll();

    /**
     * @param id id is a unique author identifier
     * @return author with given id
     */
    Artist getById(Long id);

    /**
     * @param id id of author to be deleted
     */
    void deleteById(Long id);
}
