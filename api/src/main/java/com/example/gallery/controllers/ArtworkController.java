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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.gallery.domain.Artwork;
import com.example.services.gallery.ArtworkService;

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
 * ArtworkController provides RESTful API endpoints for CRUD operations
 * on {@link Artwork} objects.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/api/gallery/artworks")
@Tag(
    name = "Artworks",
    description = "API operations related to gallery artworks")
@RequiredArgsConstructor
public class ArtworkController {

    /**
     * {@link ArtworkService} it is a business logic provider class.
     */
    private final ArtworkService artWorkService;

    /**
     * REST endpoint that retrieve all existing artworks.
     *
     * @return  retrieved {@link List} of {@link Artwork} objects and
     *          HTTP status 200
     */
    @Operation(summary = "Retrieve all artworks")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Artworks retrieved successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Artwork.class))}),
        @ApiResponse(
            responseCode = "404",
            description = "No artworks found",
            content = @Content) })
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<Artwork> getAll() {
        return artWorkService.getAll();
    }

    /**
     * REST endpoint that retrieve existing artwork by id.
     *
     * @param id {@link Long} unique identifier
     * @return  retrieved {@link Artwork} object and
     *          HTTP status 200
     */
    @Operation(summary = "Get artwork by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Artwork is retrieved successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Artwork.class))}),
        @ApiResponse(
            responseCode = "400",
            description = "Id is not valid",
            content = @Content),
        @ApiResponse(
            responseCode = "404",
            description = "Artwork with given id is not found",
            content = @Content) })
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Artwork getById(@RequestParam @NotNull @Positive final Long id) {
        return artWorkService.getById(id);
    }

    /**
     * REST endpoint that save new {@link Artwork}.
     *
     * @param artWork {@link Artwork} object to save
     * @return saved artwork with new id
     */
    @Operation(summary = "Save new artwork")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Artworks is saved successfully",
            content = {
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Artwork.class))}),
        @ApiResponse(
            responseCode = "400",
            description = "Artwork is not valid",
            content = @Content),
        @ApiResponse(
            responseCode = "400",
            description = "Artwork with given name already exists",
            content = @Content) })
    @PostMapping("/")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Artwork create(@RequestBody final @NotNull @Valid Artwork artWork) {
        return artWorkService.save(artWork);
    }

    /**
     * REST endpoint that delete artwork by id.
     *
     * @param id {@link Long} Artwork unique identifier
     */
    @Operation(summary = "Delete artwork by id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Artworks deleted successfully"),
        @ApiResponse(
            responseCode = "400",
            description = "Id is not valid"),
        @ApiResponse(
            responseCode = "404",
            description = "Artwork with given id is not found") })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @NotNull @Positive final Long id) {
        artWorkService.deleteById(id);
    }
}
