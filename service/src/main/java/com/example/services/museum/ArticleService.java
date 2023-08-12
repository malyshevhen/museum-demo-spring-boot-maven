package com.example.services.museum;

import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.dto.museum.article.ArticleWithoutContent;
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
    ArticleWithContent getById(Long id);

    /**
     * Create a new article.
     *
     * @param article The article to create.
     * @return The created article.
     * @throws IllegalArgumentException if the article is null.
     */
    ArticleWithContent save(ArticlePublishingForm article);

    /**
     * Update an existing article title.
     *
     * @param id The ID of the article to update.
     * @param title The updated article title.
     * @return The updated article.
     * @throws ArticleNotFoundException if the article is null or not found.
     */
    ArticleWithContent update(Long id, String title, String body);

    /**
     * Get a list of article DTOs with article content by author ID.
     *
     * @return ArticleWithContent List of DTOs with article content
     */
    List<ArticleWithContent> getAllWithBodyByAuthorId(Long authorId);

    /**
     * Get a list of all article DTOs without article content.
     *
     * @return ArticleWithoutContent List of DTOs without article content
     */
    List<ArticleWithoutContent> getAllWithoutBody();

    /**
     * Delete an article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    void deleteById(Long id);
}
