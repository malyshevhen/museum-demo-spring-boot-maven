package com.example.services.museum;

import com.example.museum.domain.Author;

import java.util.List;

/**
 * Service interface for handling operations related to authors.
 */
public interface AuthorService {

    /**
     * Get a list of all authors.
     *
     * @return List of Author objects representing all authors.
     */
    List<Author> getAll();

    /**
     * Get a specific author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return The Author object representing the requested author.
     */
    Author getById(Long id);

    /**
     * Create a new author.
     *
     * @param author The Author object containing the details of the new author.
     * @return The created Author object.
     */
    Author save(Author author);

    /**
     * Update an existing author.
     *
     * @param id     The ID of the author to update.
     * @param author The Author object containing the updated details.
     * @return The updated Author object.
     */
    Author update(Long id, Author author);

    /**
     * Delete an author by ID.
     *
     * @param id The ID of the author to delete.
     */
    void deleteById(Long id);
}

