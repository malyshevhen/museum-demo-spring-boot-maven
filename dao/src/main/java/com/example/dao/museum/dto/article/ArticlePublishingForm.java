package com.example.dao.museum.dto.article;

import com.example.dao.museum.domain.Article;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Article}
 */
public record ArticlePublishingForm(@NotNull @NotBlank String title,
                                    @NotNull @NotBlank String body,
                                    Set<Article.WebArticleTag> tags,
                                    @NotNull @Positive Long authorId) implements Serializable {
}