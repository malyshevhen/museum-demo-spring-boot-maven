package com.example.services.museum.impl;

import com.example.domain.museum.Article;
import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.dto.museum.article.ArticleWithoutContent;
import com.example.repositories.museum.ArticleRepository;
import com.example.repositories.museum.AuthorRepository;
import com.example.services.museum.ArticleService;
import com.example.services.museum.exceptions.ArticleNotFoundException;
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
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    public static final String ARTICLE_WITH_ID_NOT_FOUND = "Article with ID: %d not found.";

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<ArticleWithContent> getAllWithContentByAuthorId(final Long authorId) {
        var articles = articleRepository.findAllWithBodyByAuthorId(authorId);
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("No articles found");
        }
        return articles;
    }

    @Override
    public List<ArticleWithoutContent> getAllWithoutContent() {
        var articles = articleRepository.findAllWithoutContent();
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
    public ArticleWithContent getById(final Long id) {
        return articleRepository.findArticleWithContentById(id)
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
    public ArticleWithContent save(
            final ArticlePublishingForm publishingForm) {
        var author = authorRepository.findById(publishingForm.authorId())
                .orElseThrow();
        var article = new Article(
                publishingForm.title(),
                publishingForm.content(),
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
    public ArticleWithContent update(Long id, String title, String body) {
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
    public void deleteById(final Long id) {
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
