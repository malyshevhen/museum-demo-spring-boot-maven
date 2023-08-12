package com.example.domain.museum;

import com.example.constants.TestConstants;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.example.utils.InstancioDomainModels.getArticleModel;
import static com.example.utils.InstancioDomainModels.getAuthorModel;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests of Article validations.
 *
 * @author Evhen Malysh
 */
@ExtendWith(InstancioExtension.class)
class ArticleTest {
    private Validator validator;

    @WithSettings
    private final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true);

    @BeforeEach
    void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @DisplayName("No constraint violations with valid article")
    @Test
    void testPass() {
        var article = Instancio.of(getArticleModel()).create();
        var violations = validator.validate(article);

        assertTrue(violations.isEmpty());
    }

    @DisplayName("Constraint violations should be created when title is invalid")
    @ParameterizedTest
    @MethodSource
    void invalidTitle(String title) {
        var article = Instancio.of(getArticleModel()).create();
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
        var article = Instancio.of(getArticleModel()).create();
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
        var article = Instancio.of(getArticleModel()).create();
        article.setAuthor(author);

        var violations = validator.validate(article);

        assertFalse(violations.isEmpty());
    }

    private static Stream<Arguments> invalidAuthor() {

        return Stream.of(
                Arguments.of(
                        Instancio.of(getAuthorModel())
                                .set(field("username"), TestConstants.EMPTY_STRING)
                                .create())
        );
    }
}