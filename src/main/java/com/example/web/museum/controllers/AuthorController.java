package com.example.web.museum.controllers;

import com.example.dto.museum.author.AuthorRegistrationForm;
import com.example.dto.museum.author.AuthorShortResponse;
import com.example.services.museum.AuthorService;
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
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling API endpoints related to authors.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/api/museum/authors")
@Validated
@Tag(name = "Authors", description = "API operations related to authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    /**
     * Get a list of all authors.
     *
     * @return List of all authors.
     */
    @GetMapping
    @Operation(summary = "Get a list of all authors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of authors",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorShortResponse.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "No authors is found")})
    public List<AuthorShortResponse> getAll() {
        return authorService.getAllAuthors();
    }

    /**
     * Get a specific author by ID.
     *
     * @param id The ID of the author to retrieve.
     * @return The Author object representing the requested author.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a specific author by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the author",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorShortResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author not found")})
    @ResponseStatus(HttpStatus.OK)
    public AuthorShortResponse getById(@PathVariable @NotNull @Positive final Long id) {
        return authorService.getById(id);
    }

    /**
     * Create a new author.
     *
     * @param authorRegistrationForm The object that contains the details of the new author.
     * @return The created Author object.
     */
    @PostMapping
    @Operation(summary = "Create a new author")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Author created successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorShortResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Order is invalid or already exists")})
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorShortResponse create(
            @RequestBody @NotNull @Valid final AuthorRegistrationForm authorRegistrationForm) {
        return authorService.save(authorRegistrationForm);
    }

    /**
     * Update an existing author's username.
     *
     * @param id     The ID of the author to update.
     * @param username The Author object containing the updated details.
     * @return The updated Author object.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing author`s username")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Author updated successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorShortResponse.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID or author`s username is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existing author found by given ID to update")})
    @ResponseStatus(HttpStatus.OK)
    public AuthorShortResponse updateUsername(@PathVariable @NotNull @Positive final Long id,
                         @RequestBody @NotNull @Valid final String username) {
        return authorService.updateUsername(id, username);
    }

    /**
     * Delete an author by ID.
     *
     * @param id The ID of the author to delete.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an author by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Author deleted successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID id invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No author with given ID id found")})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable @NotNull @Positive final Long id) {
        authorService.deleteById(id);
    }
}
