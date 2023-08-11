package com.example.services.museum.impl;

import com.example.domain.museum.Article;
import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithBody;
import com.example.dto.museum.article.ArticleWithoutBody;
import com.example.repositories.museum.ArticleRepository;
import com.example.repositories.museum.AuthorRepository;
import com.example.services.museum.ArticleService;
import com.example.services.museum.exceptions.ArticleNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.function.Supplier;

/**
 * Service implementation for managing museum articles.
 *
 * @author Evhen Malysh
 */
@Service
@Validated
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    public static final String ARTICLE_WITH_ID_NOT_FOUND = "Article with ID: %d not found.";

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<ArticleWithBody> getAllWithBodyByAuthorId(
            @NotNull @Positive final Long authorId) {
        var articles = articleRepository.findAllWithBodyByAuthorId(authorId);
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("No articles found");
        }
        return articles;
    }

    @Override
    public List<ArticleWithoutBody> getAllWithoutBody() {
        var articles = articleRepository.findAllWithoutBody();
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("No articles found");
        }
        return articles;
    }

    /**
     * Get a specific article by its ID.
     *
     * @param id The ID of the article.
     * @return The article with the given ID, or null if not found.
     */
    @Override
    public ArticleWithBody getById(@NotNull @Positive final Long id) {
        return articleRepository.findArticleWithBodyById(id)
                .orElseThrow(getArticleNotFoundExceptionSupplier(id));
    }

    /**
     * Create a new article.
     *
     * @param publishingForm The article publishing form to save new article.
     * @return The created article.
     */
    @Override
    @Transactional
    public ArticleWithBody save(
            @NotNull @Valid final ArticlePublishingForm publishingForm) {
        var author = authorRepository.findById(publishingForm.authorId())
                .orElseThrow();
        var article = new Article(
                publishingForm.title(),
                publishingForm.body(),
                publishingForm.tags(),
                author
        );
        var savedArticleId = articleRepository.save(article).getId();
        return getById(savedArticleId);
    }

    /**
     * Update a title of an existing article.
     *
     * @param id    The ID of the article to update.
     * @param title The updated article title.
     * @return The updated article.
     * @throws ArticleNotFoundException if the article with given ID was not found.
     */
    @Override
    @Transactional
    public ArticleWithBody update(
            @NotNull @Positive final Long id,
            @NotNull @NotBlank final String title,
            @NotNull @NotBlank final String body) {
        if (isNotPresent(id)) {
            throw new ArticleNotFoundException(
                    String.format(ARTICLE_WITH_ID_NOT_FOUND, id));
        }
        articleRepository.updateTitleAndBodyById(title, body, id);
        return getById(id);
    }

    /**
     * Delete an article by its ID.
     *
     * @param id The ID of the article to delete.
     */
    @Override
    @Transactional
    public void deleteById(@NotNull @Positive final Long id) {
        if (isNotPresent(id)) {
            throw new ArticleNotFoundException(
                    String.format(ARTICLE_WITH_ID_NOT_FOUND, id));
        }
        articleRepository.deleteById(id);
    }

    private boolean isNotPresent(Long id) {
        return !articleRepository.existsById(id);
    }

    private static Supplier<ArticleNotFoundException> getArticleNotFoundExceptionSupplier(Long id) {
        return () -> new ArticleNotFoundException(
                String.format(ARTICLE_WITH_ID_NOT_FOUND, id));
    }
}
