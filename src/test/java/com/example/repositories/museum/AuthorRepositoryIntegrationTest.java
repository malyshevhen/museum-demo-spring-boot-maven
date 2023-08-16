package com.example.repositories.museum;

import com.example.config.AbstractRepositoryIntegrationTest;
import com.example.dto.museum.author.AuthorShortResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void findAllAuthors() {
        var allAuthors = authorRepository.findAllAuthors();
        allAuthors.stream()
                .map(AuthorShortResponse::id)
                .forEach(Assertions::assertNotNull);

        allAuthors.stream()
                .map(AuthorShortResponse::username)
                .forEach(Assertions::assertNotNull);

        allAuthors.stream()
                .map(AuthorShortResponse::userFirstName)
                .forEach(Assertions::assertNotNull);

        allAuthors.stream()
                .map(AuthorShortResponse::userLastName)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void findAuthorById() {
        authorRepository.findAuthorById(1L)
                .ifPresentOrElse(
                        Assertions::assertNotNull,
                        Assertions::fail
                );
    }

    @Test
    void existsByUsername() {
        assertTrue(authorRepository.existsByUsername("author1"));
        assertFalse(authorRepository.existsByUsername("WRONG"));
    }
}