package com.example.web.museum.controllers;

import com.example.dao.museum.domain.Article;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing museum articles.
 *
 * @author Evhen Malysh
 */
@RestController
@RequestMapping("/api/museum/articles")
@Tag(name = "Articles", description = "API operations related to museum articles")
@RequiredArgsConstructor
public class ArticleController {

    /**
     *
     */
    private final ArticleService articleService;

    /**
     * Get a list of all articles.
     *
     * @return List of articles.
     */
    @GetMapping
    @Operation(summary = "Get a list of all articles")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the list of articles",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))})
    @ResponseStatus(code = HttpStatus.OK)
    public List<Article> getAll() {
        return articleService.getAll();
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
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")})
    @ResponseStatus(HttpStatus.OK)
    public Article getById(
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
                            schema = @Schema(implementation = Article.class)))})
    @ResponseStatus(HttpStatus.CREATED)
    public Article create(
            @RequestBody @NotNull @Valid final Article article) {
        return articleService.save(article);
    }

    /**
     * Update an existing article.
     *
     * @param article The updated article data.
     * @return The updated article.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing article")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Article updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request or article data"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Article not found")})
    @ResponseStatus(HttpStatus.OK)
    public Article update(@RequestBody @NotNull @Valid final Article article) {
        return articleService.update(article);
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
    public void deleteById(@PathVariable final Long id) {
        articleService.deleteById(id);
    }
}
