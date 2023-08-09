package com.example.services.museum.impl;

import com.example.domain.museum.Author;
import com.example.repositories.museum.AuthorRepository;
import com.example.services.museum.AuthorService;
import com.example.services.museum.exceptions.AuthorAlreadyExistException;
import com.example.services.museum.exceptions.AuthorNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Implementation of the AuthorService interface.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    /**
     *
     */
    private final AuthorRepository authorRepository;

    private static Consumer<? super Author> getAuthorAlreadyExistExceptionConsumer() {
        return u -> {
            throw new AuthorAlreadyExistException("Author already exists");
        };
    }

    private static Supplier<AuthorNotFoundException> getAuthorNotFoundExceptionSupplier(Long id) {
        return () -> new AuthorNotFoundException(
                String.format("Author with ID: %s not found", id));
    }

    /**
     * Get a list of all authors.
     *
     * @return List of Author objects representing all authors.
     */
    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    /**
     * Get a specific author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return The Author object representing the requested author,
     * or null if not found.
     */
    @Override
    public Author getById(@NotNull @Positive final Long id) {
        return authorRepository.findById(id)
                .orElseThrow(getAuthorNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new author.
     *
     * @param author The Author object containing the details of the new author.
     * @return The created Author object.
     * @throws AuthorAlreadyExistException if Author with given users ID already exists.
     */
    @Override
    public Author save(@NotNull @Valid final Author author) {
        authorRepository.findByUserId(author.getUser().getId())
                .ifPresent(getAuthorAlreadyExistExceptionConsumer());
        return authorRepository.save(author);
    }

    /**
     * Update an existing author.
     *
     * @param id     The ID of the author to update.
     * @param author The Author object containing the updated details.
     * @return The updated Author object, or null if author not found.
     */
    @Override
    public Author update(
            @NotNull @Positive final Long id,
            @NotNull @Valid final Author author) {
        var existingAuthor = getById(id);
        existingAuthor.setUsername(author.getUsername());
        existingAuthor.setUser(author.getUser());
        return authorRepository.save(existingAuthor);
    }

    /**
     * Delete an author by ID.
     *
     * @param id The ID of the author to delete.
     */
    @Override
    public void deleteById(@NotNull @Positive final Long id) {
        var existingAuthor = getById(id);
        authorRepository.delete(existingAuthor);
    }
}
