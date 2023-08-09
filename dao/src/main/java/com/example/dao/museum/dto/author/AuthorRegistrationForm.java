package com.example.dao.museum.dto.author;

import com.example.dao.museum.domain.Author;

import java.io.Serializable;

/**
 * DTO for {@link Author}
 */
public record AuthorRegistrationForm(String username, Long userUserId) implements Serializable {
}