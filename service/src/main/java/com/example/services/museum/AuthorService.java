package com.example.services.museum;

import com.example.dto.museum.author.AuthorRegistrationForm;
import com.example.dto.museum.author.AuthorShortResponse;

import java.util.List;

/**
 * Service interface for handling operations related to authors.
 *
 * @author Evhen Malysh
 */
public interface AuthorService {

    /**
     * Get a list of all authors.
     *
     * @return List of Author objects representing all authors.
     */
    List<AuthorShortResponse> getAllAuthors();

    /**
     * Get a specific author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return The Author object representing the requested author.
     */
    AuthorShortResponse getById(Long id);

    /**
     * Create a new author.
     *
     * @param authorRegistrationForm The Author object containing the details of the new author.
     * @return The created Author object.
     */
    AuthorShortResponse save(AuthorRegistrationForm authorRegistrationForm);

    /**
     * Update an existing author`s username.
     *
     * @param id     The ID of the author to update.
     * @param username The author`s username to update.
     * @return The updated Author object.
     */
    AuthorShortResponse updateUsername(Long id, String username);

    /**
     * Delete an author by ID.
     *
     * @param id The ID of the author to delete.
     */
    void deleteById(Long id);
}

