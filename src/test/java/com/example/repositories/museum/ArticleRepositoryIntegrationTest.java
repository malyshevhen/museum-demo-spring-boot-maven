package com.example.repositories.museum;

import com.example.config.AbstractRepositoryIntegrationTest;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.dto.museum.article.ArticleWithoutContent;
import com.example.repositories.museum.ArticleRepository.ArticleDbRowWithContent;
import com.example.repositories.museum.ArticleRepository.ArticleDbRowWithoutContent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private ArticleRepository repository;

    @Test
    void loadAllArticlesDataWithContentByAuthorId() {
        var rows = repository.loadAllArticlesDataWithContentByAuthorId(1L);

        rows.stream()
                .map(ArticleDbRowWithContent::id)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithContent::title)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithContent::content)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithContent::authorId)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithContent::authorUsername)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithContent::createdAt)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void loadArticleTagsById() {
        var tags = repository.loadArticleTagsById(1L);

        assertFalse(tags.isEmpty());
    }

    @Test
    void findAllWithBodyByAuthorId() {
        var articlesWithContent = repository.findAllWithBodyByAuthorId(1L);

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

        articlesWithContent.stream()
                .map(ArticleWithContent::tags)
                .map(Set::isEmpty)
                .forEach(Assertions::assertFalse);
    }

    @Test
    void loadAllArticleDataWithoutContent() {
        var rows = repository.loadAllArticleDataWithoutContent();

        rows.stream()
                .map(ArticleDbRowWithoutContent::id)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithoutContent::title)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithoutContent::authorId)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithoutContent::authorUsername)
                .forEach(Assertions::assertNotNull);

        rows.stream()
                .map(ArticleDbRowWithoutContent::createdAt)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void findAllWithoutContent() {
        var articlesWithoutContent = repository.findAllWithoutContent();

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
                .map(Set::isEmpty).anyMatch(b -> b.equals(true)));
    }
}