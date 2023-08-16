package com.example.domain.museum;

import com.example.constants.TestConstants;
import com.example.domain.config.AbstractDomainModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of Article validations.
 *
 * @author Evhen Malysh
 */
class ArticleDomainModelTest extends AbstractDomainModelTest {

    @DisplayName("No constraint violations with valid article")
    @Test
    void testPass() {
        var article = getValidArticle();
        var violations = validate(article);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when title is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidTitle(String title) {
        var article = getValidArticle();
        article.setTitle(title);

        var violations = validate(article);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidTitle() {
        return Stream.of(
                Arguments.of(TestConstants.EMPTY_STRING),
                Arguments.of(TestConstants.Articles.UNDERSIZED_TITLE_FIELD),
                Arguments.of(TestConstants.Articles.OVERSIZED_TITLE_FIELD));
    }

    @DisplayName("Constraint violations should be created when body is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidContent(String body) {
        var article = getValidArticle();
        article.setContent(body);

        var violations = validate(article);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidContent() {
        return Stream.of(
                Arguments.of(TestConstants.EMPTY_STRING),
                Arguments.of(TestConstants.Articles.UNDERSIZED_CONTENT_FIELD),
                Arguments.of(TestConstants.Articles.OVERSIZED_CONTENT_FIELD));
    }


    @DisplayName("Constraint violations should be created when author is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidAuthor(Author author) {
        var article = getValidArticle();
        article.setAuthor(author);

        var violations = validate(article);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidAuthor() {
        var author = getValidAuthor();
        author.setUsername("");
        return Stream.of(Arguments.of(author));
    }
}