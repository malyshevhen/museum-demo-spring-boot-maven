package com.example.services.museum.impl;

import com.example.config.AbstractServiceIntegrationTest;
import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.dto.museum.article.ArticleWithoutContent;
import com.example.services.museum.ArticleService;
import com.example.services.museum.exceptions.ArticleNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.example.domain.museum.Article.ArticleTag.ARCHAEOLOGY;
import static com.example.domain.museum.Article.ArticleTag.ART_HISTORY;
import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceImplIntegrationTest extends AbstractServiceIntegrationTest<ArticlePublishingForm> {

    @Autowired
    private ArticleService articleService;

    @AfterEach
    void tearDown() {
        clearAdditionalSettings();
    }

    @Test
    void getAllWithBodyByAuthorId() {
        var articlesWithContent = articleService.getAllWithContentByAuthorId(1L);

        articlesWithContent.stream()
                .map(ArticleWithContent::id)
                .forEach(Assertions::assertNotNull);

        articlesWithContent.stream()
                .map(ArticleWithContent::title)
                .forEach(Assertions::assertNotNull);

        articlesWithContent.stream()
                .map(ArticleWithContent::content)
                .forEach(Assertions::assertNotNull);

        articlesWithContent.stream()
                .map(ArticleWithContent::authorId)
                .forEach(Assertions::assertNotNull);

        articlesWithContent.stream()
                .map(ArticleWithContent::authorUsername)
                .forEach(Assertions::assertNotNull);

        articlesWithContent.stream()
                .map(ArticleWithContent::createdAt)
                .forEach(Assertions::assertNotNull);

        assertTrue(articlesWithContent.stream()
                .map(ArticleWithContent::tags)
                .map(Set::isEmpty)
                .anyMatch(b -> b.equals(false)));
    }

    @Test
    void getAllWithoutBody() {
        var articlesWithoutContent = articleService.getAllWithoutContent();

        articlesWithoutContent.stream()
                .map(ArticleWithoutContent::id)
                .forEach(Assertions::assertNotNull);

        articlesWithoutContent.stream()
                .map(ArticleWithoutContent::title)
                .forEach(Assertions::assertNotNull);

        articlesWithoutContent.stream()
                .map(ArticleWithoutContent::authorId)
                .forEach(Assertions::assertNotNull);

        articlesWithoutContent.stream()
                .map(ArticleWithoutContent::authorUsername)
                .forEach(Assertions::assertNotNull);

        articlesWithoutContent.stream()
                .map(ArticleWithoutContent::createdAt)
                .forEach(Assertions::assertNotNull);

        assertTrue(articlesWithoutContent.stream()
                .map(ArticleWithoutContent::tags)
                .map(Set::isEmpty)
                .anyMatch(b -> b.equals(false)));
    }

    @Test
    void getById() {
        var articleWithContent = articleService.getById(1L);

        assertNotNull(articleWithContent.id());
        assertNotNull(articleWithContent.content());
        assertNotNull(articleWithContent.title());
        assertNotNull(articleWithContent.tags());
        assertNotNull(articleWithContent.authorId());
        assertNotNull(articleWithContent.authorUsername());
        assertNotNull(articleWithContent.createdAt());
    }

    @Test
    void save() {
        set("authorId", 1L);
        set("tags", Set.of(ARCHAEOLOGY, ART_HISTORY));
        var articleToSave = getModel();

        var savedArticle = articleService.save(articleToSave);

        assertNotNull(savedArticle.id());
        assertNotNull(savedArticle.content());
        assertNotNull(savedArticle.title());
        assertNotNull(savedArticle.tags());
        assertNotNull(savedArticle.authorId());
        assertNotNull(savedArticle.authorUsername());
        assertNotNull(savedArticle.createdAt());
    }

    @Test
    void update() {
        var article = getModel();
        var title = article.title();
        var content = article.content();

        var updatedArticle = articleService.update(1L, title, content);

        assertEquals(title, updatedArticle.title());
        assertEquals(content, updatedArticle.content());
    }

    @Test
    void deleteById() {
        long id = 1L;
        articleService.deleteById(id);

        assertThrows(ArticleNotFoundException.class, () -> articleService.getById(id));
    }
}