package com.example.dao.museum.dto.author;

import com.example.dao.museum.domain.Author;
import com.example.dao.museum.dto.article.ArticleWithoutBody;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Author}
 */
public record AuthorWithArticles(@NotNull @Positive Long id,
                                 @NotNull @NotBlank String username,
                                 @NotNull @Positive Long userId,
                                 @NotNull @NotBlank String userFirstName,
                                 @NotNull @NotBlank String userLastName,
                                 Set<ArticleWithoutBody> articles) implements Serializable {
}