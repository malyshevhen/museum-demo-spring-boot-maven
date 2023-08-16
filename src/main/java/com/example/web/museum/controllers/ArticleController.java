package com.example.web.museum.controllers;

import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.dto.museum.article.ArticleWithoutContent;
import com.example.services.museum.ArticleService;
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
 * REST controller for managing museum articles.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/articles")
@Validated
@Tag(name = "Articles", description = "API operations related to museum articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * Get a list of all articles with content.
     *
     * @return List of articles.
     */
    @GetMapping("/by-author/{authorId}")
    @Operation(summary = "Get a list of all articles with content by author ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of articles",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ArticleWithContent.class)))})
    @ResponseStatus(code = HttpStatus.OK)
    public List<ArticleWithContent> getAllWithBodyByAuthorId(
            @PathVariable @NotNull @Positive final Long authorId) {
        return articleService.getAllWithContentByAuthorId(authorId);
    }

    /**
     * Get a list of all articles without content.
     *
     * @return List of articles.
     */
    @GetMapping
    @Operation(summary = "Get a list of all articles without content")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of articles",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ArticleWithoutContent.class)))})
    @ResponseStatus(code = HttpStatus.OK)
    public List<ArticleWithoutContent> getAllWithoutBody() {
        return articleService.getAllWithoutContent();
    }

    /**
     * Get a specific article by its ID.
     *
     * @param id The ID of the article.
     * @return The article with the given ID.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a specific article by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the article",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ArticleWithContent.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")})
    @ResponseStatus(HttpStatus.OK)
    public ArticleWithContent getById(
            @PathVariable @NotNull @Positive final Long id) {
        return articleService.getById(id);
    }

    /**
     * Create a new article.
     *
     * @param article The article to create.
     * @return The created article.
     */
    @PostMapping
    @Operation(summary = "Create a new article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Article created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ArticleWithContent.class)))})
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleWithContent create(
            @RequestBody @NotNull @Valid final ArticlePublishingForm article) {
        return articleService.save(article);
    }

    /**
     * Update a title of an existing article.
     *
     * @param title The updated article title.
     * @param body The updated article content.
     * @return The updated article.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing article title")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Article title updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ArticleWithContent.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid article ID or data"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")})
    @ResponseStatus(HttpStatus.OK)
    public ArticleWithContent update(
            @PathVariable @NotNull @Positive final Long id,
            @RequestParam @NotNull @Valid final String title,
            @RequestParam @NotNull @Valid final String body
    ) {
        return articleService.update(id, title, body);
    }

    /**
     * Delete an article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an article by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Article deleted successfully"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id is not valid"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @NotNull @Positive final Long id) {
        articleService.deleteById(id);
    }
}
