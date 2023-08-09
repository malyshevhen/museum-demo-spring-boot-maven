package com.example.web.museum.controllers;

import com.example.domain.museum.Author;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling API endpoints related to authors.
 */
@RestController
@RequestMapping("/api/museum/authors")
@Tag(name = "Authors", description = "API operations related to authors")
@RequiredArgsConstructor
public class AuthorController {

    /**
     *
     */
    private final AuthorService authorService;

    /**
     * Get a list of all authors.
     *
     * @return List of Author objects representing all authors.
     */
    @GetMapping
    @Operation(summary = "Get a list of all authors")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of authors",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "No authors is found")})
    public List<Author> getAll() {
        return authorService.getAll();
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
                            schema = @Schema(implementation = Author.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Author not found")})
    @ResponseStatus(HttpStatus.OK)
    public Author getById(@PathVariable @NotNull @Positive final Long id) {
        return authorService.getById(id);
    }

    /**
     * Create a new author.
     *
     * @param author The Author object containing the details of the new author.
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
                            schema = @Schema(implementation = Author.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Order is invalid or already exists")})
    @ResponseStatus(HttpStatus.CREATED)
    public Author create(@RequestBody @NotNull @Valid final Author author) {
        return authorService.save(author);
    }

    /**
     * Update an existing author.
     *
     * @param id     The ID of the author to update.
     * @param author The Author object containing the updated details.
     * @return The updated Author object.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing author")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Author updated successfully",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Author.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID or Author is invalid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existing author found by given ID to update")})
    @ResponseStatus(HttpStatus.OK)
    public Author update(@PathVariable @NotNull @Positive final Long id,
                         @RequestBody @NotNull @Valid final Author author) {
        return authorService.update(id, author);
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
