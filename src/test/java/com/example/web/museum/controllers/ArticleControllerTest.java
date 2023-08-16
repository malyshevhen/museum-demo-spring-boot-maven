package com.example.web.museum.controllers;

import com.example.dto.museum.article.ArticlePublishingForm;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.services.museum.ArticleService;
import com.example.services.museum.exceptions.ArticleNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static com.example.domain.museum.Article.ArticleTag.*;
import static org.instancio.Select.field;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ArticleController.class)
@ExtendWith(InstancioExtension.class)
class ArticleControllerTest {
    @WithSettings
    private static final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true)
            .lock();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ArticleService articleService;

    @Test
    void getAll_status_is_ok() throws Exception {
        mockMvc.perform(get("/articles"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_status_is_notfound() throws Exception {
        when(articleService.getAllWithoutContent()).thenThrow(ArticleNotFoundException.class);

        mockMvc.perform(get("/articles"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_status_is_ok() throws Exception {
        var article = Instancio.of(ArticleWithContent.class)
                .withSettings(settings)
                .create();

        when(articleService.getById(article.id())).thenReturn(article);

        mockMvc.perform(get("/articles/{id}", article.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(article.id()))
                .andExpect(jsonPath("title").value(article.title()))
                .andExpect(jsonPath("content").value(article.content()))
                .andExpect(jsonPath("authorId").value(article.authorId()))
                .andExpect(jsonPath("authorUsername").value(article.authorUsername()))
                .andExpect(jsonPath("createdAt").value(article.createdAt().toString()));
    }

    @Test
    void getById_status_is_notfound() throws Exception {
        long id = 1L;

        when(articleService.getById(id)).thenThrow(ArticleNotFoundException.class);

        mockMvc.perform(get("/articles/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_status_is_bad_request() throws Exception {
        long id = -1L;

        mockMvc.perform(get("/articles/{id}", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_status_is_created() throws Exception {
        var articleForm = Instancio.of(ArticlePublishingForm.class)
                .set(field("tags"), Set.of(ARCHAEOLOGY, EXHIBITIONS, INTERVIEWS, EVENTS))
                .withSettings(settings)
                .create();

        var article = new ArticleWithContent(
                1L,
                articleForm.title(),
                articleForm.content(),
                articleForm.tags(),
                articleForm.authorId(),
                "Foo Bar",
                LocalDateTime.now()
        );

        when(articleService.save(articleForm)).thenReturn(article);

        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(articleForm)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(article.id()))
                .andExpect(jsonPath("title").value(article.title()))
                .andExpect(jsonPath("content").value(article.content()))
                .andExpect(jsonPath("authorId").value(article.authorId()))
                .andExpect(jsonPath("authorUsername").value(article.authorUsername()))
                .andExpect(jsonPath("createdAt").value(article.createdAt().toString()));
    }

    @Test
    void create_status_is_bad_request() throws Exception {
        var publishingForm = Instancio.of(ArticlePublishingForm.class)
                .set(field("title"), "")
                .ignore(field("tags"))
                .withSettings(settings)
                .create();

        mockMvc.perform(post("/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(publishingForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_status_is_no_content() throws Exception {
        mockMvc.perform(delete("/articles/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_status_is_bad_request() throws Exception {
        mockMvc.perform(delete("/articles/{id}", -1L))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_status_is_notfound() throws Exception {
        long id = 1L;

        doThrow(ArticleNotFoundException.class).when(articleService).deleteById(id);

        mockMvc.perform(delete("/articles/{id}", id))
                .andExpect(status().isNotFound());
    }
}