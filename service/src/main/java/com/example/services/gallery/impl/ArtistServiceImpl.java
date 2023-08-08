// @formatter:off

package com.example.services.gallery.impl;

import java.util.List;

import com.example.gallery.domain.Artist;
import com.example.services.gallery.ArtistService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gallery.repositories.ArtistRepository;
import com.example.services.gallery.exceptions.AuthorNotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

/**
 * @author Evhen Malysh
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    /**
     *
     */
    private final ArtistRepository authorRepository;

    /**
     * @param artist artist to save
     * @return saved artist with id
     */
    @Override
    @Transactional
    public Artist save(final @NotNull @Valid Artist artist) {
        return authorRepository.save(artist);
    }

    /**
     * @return list of all existing authors
     */
    @Override
    public List<Artist> getAll() {
        var authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            throw new AuthorNotFoundException("No authors exists");
        }
        return authors;
    }

    /**
     * @param id id is a unique author identifier
     * @return author with given id
     */
    @Override
    public Artist getById(final @NotNull @Positive Long id) {
        return authorRepository.findById(id)
            .orElseThrow(() -> new AuthorNotFoundException(
                String.format("Artist with id: %d not found", id)
            ));
    }

    /**
     * @param id id of author to be deleted
     */
    @Override
    @Transactional
    public void deleteById(final @NotNull @Positive Long id) {
        var author = getById(id);
        authorRepository.delete(author);
    }

}
