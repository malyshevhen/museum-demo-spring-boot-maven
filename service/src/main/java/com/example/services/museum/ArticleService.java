package com.example.services.museum;

import java.util.List;

import com.example.dao.museum.domain.Article;

/**
 * Service interface for managing museum articles.
 *
 * @author Evhen Malysh
 */
public interface ArticleService {

    /**
     * Get a list of all articles.
     *
     * @return List of articles.
     */
    List<Article> getAll();

    /**
     * Get a specific article by its ID.
     *
     * @param id The ID of the article.
     * @return The article with the given ID, or null if not found.
     */
    Article getById(Long id);

    /**
     * Create a new article.
     *
     * @param article The article to create.
     * @return The created article.
     * @throws IllegalArgumentException if the article is null.
     */
    Article save(Article article);

    /**
     * Update an existing article.
     *
     * @param article The updated article data.
     * @return The updated article.
     * @throws IllegalArgumentException if the article is null or not found.
     */
    Article update(Article article);

    /**
     * Delete an article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    void deleteById(Long id);
}
