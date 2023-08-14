package com.example.domain.museum;

import com.example.constants.TestConstants;
import com.example.utils.FakeModelFactory;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
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
class ArticleTest {
    private Validator validator;

    @BeforeEach
    void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @DisplayName("No constraint violations with valid article")
    @Test
    void testPass() {
        var article = FakeModelFactory.getValidArticle();
        var violations = validator.validate(article);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when title is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidTitle(String title) {
        var article = FakeModelFactory.getValidArticle();
        article.setTitle(title);

        var violations = validator.validate(article);

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
        var article = FakeModelFactory.getValidArticle();
        article.setContent(body);

        var violations = validator.validate(article);

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
        var article = FakeModelFactory.getValidArticle();
        article.setAuthor(author);

        var violations = validator.validate(article);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidAuthor() {
        var author = FakeModelFactory.getValidAuthor();
        author.setUsername("");
        return Stream.of(Arguments.of(author));
    }
}