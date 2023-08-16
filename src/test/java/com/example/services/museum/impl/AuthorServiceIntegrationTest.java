package com.example.services.museum.impl;

import com.example.config.AbstractServiceIntegrationTest;
import com.example.dto.museum.author.AuthorRegistrationForm;
import com.example.dto.museum.author.AuthorShortResponse;
import com.example.services.museum.AuthorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthorServiceIntegrationTest extends AbstractServiceIntegrationTest<AuthorRegistrationForm> {

    @Autowired
    private AuthorService authorService;

    @AfterEach
    void tearDown() {
        clearAdditionalSettings();
    }

    @Test
    void getAllAuthors() {
        var authors = authorService.getAllAuthors();

        authors.stream()
                .map(AuthorShortResponse::id)
                .forEach(Assertions::assertNotNull);

        authors.stream()
                .map(AuthorShortResponse::username)
                .forEach(Assertions::assertNotNull);

        authors.stream()
                .map(AuthorShortResponse::userFirstName)
                .forEach(Assertions::assertNotNull);

        authors.stream()
                .map(AuthorShortResponse::userLastName)
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void getById() {
        var authorShortResponse = authorService.getById(1L);

        assertNotNull(authorShortResponse.id());
        assertNotNull(authorShortResponse.username());
        assertNotNull(authorShortResponse.userFirstName());
        assertNotNull(authorShortResponse.userLastName());
    }

    @Test
    void save() {
        set("userId", 6L);
        var authorForm = getModel();

        var authorShortResponse = authorService.save(authorForm);

        assertNotNull(authorShortResponse.id());
        assertNotNull(authorShortResponse.username());
        assertNotNull(authorShortResponse.userFirstName());
        assertNotNull(authorShortResponse.userLastName());
    }

    @Test
    void updateUsername() {
        var usernameToUpdate = "updated";
        var updatedAuthor = authorService.updateUsername(1L, usernameToUpdate);
        var updatedUsername = updatedAuthor.username();

        assertEquals(usernameToUpdate, updatedUsername);
    }
}