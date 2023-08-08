// @formatter:off

package com.example.gallery.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gallery.domain.Artist;
import com.example.gallery.domain.Artwork;
import com.example.services.gallery.ArtistService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;

/**
 * ArtController provides RESTful API endpoints for CRUD operations
 * on {@link Artist} objects.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/api/gallery/artists")
@Tag(
    name = "Artists",
    description = "API operations related to gallery Artists")
@RequiredArgsConstructor
public class ArtistController {

    /**
     * {@link ArtistService} is a business logic provider class.
     */
    private final ArtistService authorService;

    /**
     * REST endpoint for retrieving all existing authors.
     *
     * @return list of existing authors
     */
    @Operation(summary = "Retrieve all artists")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authors retrieved successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Artist.class)) }),
        @ApiResponse(
            responseCode = "404",
            description = "No author found") })
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Artist> getAll() {
        return authorService.getAll();
    }

    /**
     * REST endpoint for retrieving author by id.
     *
     * @param id id of retrieving author
     * @return author with the given id
     */
    @Operation(summary = "Retrieve artist by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Artist is retrieved successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Artwork.class)) }),
        @ApiResponse(
            responseCode = "400",
            description = "Id is not valid"),
        @ApiResponse(
            responseCode = "404",
            description = "Artist with given id is not found") })
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Artist getById(@PathVariable final @NotNull @Positive Long id) {
        return authorService.getById(id);
    }

    /**
     * REST endpoint for saving new artist.
     *
     * @param artist artist to save
     * @return id artist to be saved
     */
    @Operation(summary = "Save new artist")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Artist is saved successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Artwork.class)) }),
        @ApiResponse(
            responseCode = "400",
            description = "Artist is not valid") })
    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Artist create(@RequestBody final @NotNull @Valid Artist artist) {
        return authorService.save(artist);
    }

    /**
     * REST endpoint for deleting author by id.
     *
     * @param id id author to be deleted
     */
    @Operation(summary = "Delete artist by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Artist deleted successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Id is not valid"),
        @ApiResponse(
            responseCode = "404",
            description = "Artist with given id is not found") })
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable final @NotNull @Positive Long id) {
        authorService.deleteById(id);
    }
}
