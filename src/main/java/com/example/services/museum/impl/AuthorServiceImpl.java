package com.example.services.museum.impl;

import com.example.domain.museum.Author;
import com.example.dto.museum.author.AuthorRegistrationForm;
import com.example.dto.museum.author.AuthorShortResponse;
import com.example.repositories.museum.AuthorRepository;
import com.example.repositories.users.UserRepository;
import com.example.services.museum.AuthorService;
import com.example.services.museum.exceptions.AuthorAlreadyExistException;
import com.example.services.museum.exceptions.AuthorNotFoundException;
import com.example.services.users.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.function.Supplier;

/**
 * Implementation of the AuthorService interface.
 *
 * @author Evhen Malysh
 */
@Service
@Validated
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    public static final String AUTHOR_WITH_ID_NOT_FOUND = "Author with ID: %d not found.";
    private static final String NO_AUTHORS_WAS_FOUND = "No authors was found.";
    private static final String AUTHOR_ALREADY_EXISTS = "Author already exists";

    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;

    private static Supplier<AuthorNotFoundException> getAuthorNotFoundExceptionSupplier(Long id) {
        return () -> new AuthorNotFoundException(
                String.format(AUTHOR_WITH_ID_NOT_FOUND, id));
    }

    /**
     * Get a list of all authors.
     *
     * @return List of Author objects representing all authors.
     * @throws AuthorAlreadyExistException if Author with given users ID already exists.
     */
    @Override
    public List<AuthorShortResponse> getAllAuthors() {
        var allAuthors = authorRepository.findAllAuthors();
        if (allAuthors.isEmpty()) {
            throw new AuthorNotFoundException(NO_AUTHORS_WAS_FOUND);
        }
        return allAuthors;
    }

    /**
     * Get a specific author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return The Author object representing the requested author.
     * @throws AuthorAlreadyExistException if Author with given users ID already exists.
     */
    @Override
    public AuthorShortResponse getById(final Long id) {
        return authorRepository.findAuthorById(id)
                .orElseThrow(getAuthorNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new author.
     *
     * @param authorRegistrationForm The Author object containing the details of the new author.
     * @return The created Author object.
     * @throws AuthorAlreadyExistException if Author with given username or users ID is already exists.
     * @throws UserNotFoundException       if user with given ID is not exists.
     */
    @Override
    @Transactional
    public AuthorShortResponse save(
            final AuthorRegistrationForm authorRegistrationForm) {
        var username = authorRegistrationForm.username();
        var userId = authorRegistrationForm.userId();
        if (isPresent(username) || isPresent(userId)) {
            throw new AuthorAlreadyExistException(AUTHOR_ALREADY_EXISTS);
        }
        var user = userRepository.findById(userId)
                .orElseThrow();
        var authorToSave = new Author(username, user);
        var savedAuthor = authorRepository.save(authorToSave);
        return getById(savedAuthor.getId());
    }

    /**
     * Update an existing author`s username.
     *
     * @param id       The ID of the author to update.
     * @param username The author`s username to update.
     * @return The updated author.
     * @throws AuthorNotFoundException if author with given id is not found.
     */
    @Override
    @Transactional
    public AuthorShortResponse updateUsername(final Long id, final String username) {
        if (!isPresent(id)) {
            throw new AuthorNotFoundException(
                    String.format(AUTHOR_WITH_ID_NOT_FOUND, id));
        }
        authorRepository.updateUsernameById(username, id);
        return getById(id);
    }

    private boolean isPresent(Long id) {
        return authorRepository.existsById(id);
    }

    private boolean isPresent(String username) {
        return authorRepository.existsByUsername(username);
    }
}
