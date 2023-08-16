package com.example.dto.museum.article;

import com.example.domain.museum.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Set;

import static com.example.constraints.museum.ArticleConstraints.*;
import static com.example.constraints.museum.ArticleConstraints.MAX_TITLE_LENGTH;

/**
 * DTO for {@link Article}
 */
@Validated
public record ArticlePublishingForm(

        @NotNull
        @NotBlank
        @Size(min = MIN_TITLE_LENGTH, max = MAX_TITLE_LENGTH)
        String title,

        @NotNull
        @NotBlank
        @Size(min = MIN_CONTENT_LENGTH, max = MAX_CONTENT_LENGTH)
        String content,

        Set<Article.ArticleTag> tags,

        @NotNull
        @Positive
        Long authorId

) implements Serializable {
}