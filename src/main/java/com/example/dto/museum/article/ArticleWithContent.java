package com.example.dto.museum.article;

import com.example.domain.museum.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Article}
 */
@Validated
public record ArticleWithContent(
        @NotNull @Positive Long id,
        @NotNull @NotBlank String title,
        @NotNull @NotBlank String content,
        Set<Article.ArticleTag> tags,
        @NotNull @Positive Long authorId,
        @NotNull @NotBlank String authorUsername,
        @NotNull LocalDateTime createdAt
) implements Serializable {
}