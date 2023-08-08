// @formatter:off

package com.example.services.gallery.impl;

import com.example.gallery.domain.Artwork;
import com.example.gallery.repositories.ArtworkRepository;
import com.example.services.gallery.ArtworkService;
import com.example.services.gallery.exceptions.ArtworkAlreadyExistException;
import com.example.services.gallery.exceptions.ArtworkNotFoundException;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Consumer;

/**
 * ArtWorkServiceImpl implements {@link ArtworkService} contract
 * and provide business logic and validation
 * for operations with {@link Artwork} objects.
 *
 * @author Evhen Malysh
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArtworkServiceImpl implements ArtworkService {

    /**
     * Data access object for interaction with database.
     * It provides functionality for CRUD operations with
     * {@link Artwork} objects.
     */
    private final ArtworkRepository artRepository;

    /**
     * Save method validate {@link Artwork} object and
     * pass it to {@link ArtworkRepository} to save in DB.
     *
     * @param artwork   {@link Artwork} to save
     * @return          retrieved {@link Artwork} object
     * @throws          {@link ArtworkAlreadyExistException} if artwork
     *                  with given name is already exists
     */
    @Override
    @Transactional
    public @NotNull Artwork save(final @NotNull Artwork artwork) {
        artRepository.findByName(artwork.getName())
                .ifPresent(throwAlreadyExistException(artwork));
        return artRepository.save(artwork);
    }

    /**
     * GetAll method call ArtworkRepository.findAll()
     * and validate returned list to be not empty.
     *
     * @return  retrieved {@link List} of {@link Artwork} objects
     * @throws  {@link ArtworkNotFoundException} if list is empty
     */
    @Override
    public @NotNull List<Artwork> getAll() {
        var artworks = artRepository.findAll();
        if (artworks.isEmpty()) {
            throw new ArtworkNotFoundException("No artworks found");
        }
        return artworks;
    }

    /**
     * @param id    unique artwork identifier
     * @return      retrieved {@link Artwork} object
     * @throws      {@link ArtworkNotFoundException} if object
     *              is not present in DB
     */
    @Override
    public @NotNull Artwork getById(final @NotNull Long id) {
        return artRepository.findById(id)
                .orElseThrow(() -> new ArtworkNotFoundException(
                        String.format("Artwork with id: %d not found", id)));
    }

    /**
     * @param id    {@link Long} unique artwork identifier
     * @throws      {@link ArtworkNotFoundException} if object
     *              is not present in DB
     */
    @Override
    @Transactional
    public void deleteById(final @NotNull Long id) {
        var artwork = getById(id);
        artRepository.delete(artwork);
    }

    private Consumer<? super Artwork> throwAlreadyExistException(
            final Artwork artwork) {
        return a -> {
            throw new ArtworkAlreadyExistException(
                    String.format(
                            "Artwork with name: %s"
                            + " is already exists with id: %d",
                            artwork.getName(),
                            a.getId()));
        };
    }
}
