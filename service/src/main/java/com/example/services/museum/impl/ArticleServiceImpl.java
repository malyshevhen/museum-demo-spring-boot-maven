package com.example.services.museum.impl;

import com.example.dao.museum.domain.Article;
import com.example.dao.museum.repositories.ArticleRepository;
import com.example.services.museum.ArticleService;
import com.example.services.museum.exceptions.ArticleNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

/**
 * Service implementation for managing museum articles.
 *
 * @author Evhen Malysh
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    /**
     *
     */
    private final ArticleRepository articleRepository;

    private static Supplier<ArticleNotFoundException> getArticleNotFoundExceptionSupplier(Long id) {
        return () -> new ArticleNotFoundException(
                String.format("Article with ID: %d not found.", id));
    }

    /**
     * Get a list of all articles.
     *
     * @return List of articles.
     */
    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    /**
     * Get a specific article by its ID.
     *
     * @param id The ID of the article.
     * @return The article with the given ID, or null if not found.
     */
    @Override
    public Article getById(@NotNull @Positive final Long id) {
        return articleRepository.findById(id)
                .orElseThrow(getArticleNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new article.
     *
     * @param article The article to create.
     * @return The created article.
     * @throws IllegalArgumentException has an existing ID.
     */
    @Override
    public Article save(@NotNull final Article article) {
        if (article.getId() != null) {
            throw new IllegalArgumentException(
                    "Article ID must be null for new articles.");
        }
        return articleRepository.save(article);
    }

    /**
     * Update an existing article.
     *
     * @param article The updated article data.
     * @return The updated article.
     * @throws IllegalArgumentException if the article ID is null.
     */
    @Override
    public Article update(@NotNull @Valid final Article article) {
        if (article.getId() == null) {
            throw new IllegalArgumentException("Invalid article ID.");
        }
        return articleRepository.save(article);
    }

    /**
     * Delete an article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    @Override
    public void deleteById(@NotNull @Positive final Long id) {
        var existingArticle = getById(id);
        articleRepository.delete(existingArticle);
    }
}
