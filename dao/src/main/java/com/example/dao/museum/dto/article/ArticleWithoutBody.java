package com.example.dao.museum.dto.article;

import com.example.dao.museum.domain.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * DTO for {@link Article}
 */
public record ArticleWithoutBody(@NotNull @Positive Long id,
                                 @NotNull @NotBlank String title,
                                 Set<Article.ArticleTag> tags,
                                 @NotNull @Positive Long authorId,
                                 @NotNull @NotBlank String authorUsername,
                                 @NotNull LocalDateTime createdAt) implements Serializable {
}