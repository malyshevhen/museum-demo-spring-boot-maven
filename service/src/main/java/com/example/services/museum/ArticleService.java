package com.example.services.museum;

import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithBody;
import com.example.dto.museum.article.ArticleWithoutBody;
import com.example.services.museum.exceptions.ArticleNotFoundException;

import java.util.List;

/**
 * Service interface for managing museum articles.
 *
 * @author Evhen Malysh
 */
public interface ArticleService {

    /**
     * Get a specific article by its ID.
     *
     * @param id The ID of the article.
     * @return The article with the given ID, or null if not found.
     */
    ArticleWithBody getById(Long id);

    /**
     * Create a new article.
     *
     * @param article The article to create.
     * @return The created article.
     * @throws IllegalArgumentException if the article is null.
     */
    ArticleWithBody save(ArticlePublishingForm article);

    /**
     * Update an existing article title.
     *
     * @param id The ID of the article to update.
     * @param title The updated article title.
     * @return The updated article.
     * @throws ArticleNotFoundException if the article is null or not found.
     */
    ArticleWithBody update(Long id, String title, String body);

    /**
     * Get a list of article DTOs with article body by author ID.
     *
     * @return ArticleWithBody List of DTOs with article body
     */
    List<ArticleWithBody> getAllWithBodyByAuthorId(Long authorId);

    /**
     * Get a list of all article DTOs without article body.
     *
     * @return ArticleWithoutBody List of DTOs without article body
     */
    List<ArticleWithoutBody> getAllWithoutBody();

    /**
     * Delete an article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    void deleteById(Long id);
}
