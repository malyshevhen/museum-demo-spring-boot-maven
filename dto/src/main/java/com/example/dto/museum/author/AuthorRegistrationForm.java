package com.example.dto.museum.author;


import com.example.domain.museum.Author;

import java.io.Serializable;

/**
 * DTO for {@link Author}
 */
public record AuthorRegistrationForm(String username, Long userUserId) implements Serializable {
}