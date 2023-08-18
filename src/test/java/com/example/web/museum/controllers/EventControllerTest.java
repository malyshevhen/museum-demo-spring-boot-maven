package com.example.web.museum.controllers;

import com.example.domain.museum.Event;
import com.example.dto.museum.event.EventPublishingForm;
import com.example.dto.museum.event.EventWithContent;
import com.example.services.museum.EventService;
import com.example.services.museum.exceptions.EventNotFoundException;
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

import static org.instancio.Select.field;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EventController.class)
@ExtendWith(InstancioExtension.class)
class EventControllerTest {

    @WithSettings
    private static final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true)
            .lock();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EventService eventService;

    @Test
    void getAll_status_is_ok() throws Exception {
        mockMvc.perform(get("/events"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_status_is_notfound() throws Exception {
        when(eventService.getAll()).thenThrow(EventNotFoundException.class);

        mockMvc.perform(get("/events"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_status_is_ok() throws Exception {
        var event = Instancio.of(EventWithContent.class)
                .withSettings(settings)
                .create();

        when(eventService.getById(event.id())).thenReturn(event);

        mockMvc.perform(get("/events/{id}", event.id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(event.id()))
                .andExpect(jsonPath("status").value(event.status().toString()))
                .andExpect(jsonPath("title").value(event.title()))
                .andExpect(jsonPath("content").value(event.content()))
                .andExpect(jsonPath("capacity").value(event.capacity()))
                .andExpect(jsonPath("authorId").value(event.authorId()))
                .andExpect(jsonPath("authorUsername").value(event.authorUsername()));
    }

    @Test
    void getById_status_is_notfound() throws Exception {
        long id = 1L;

        when(eventService.getById(id)).thenThrow(EventNotFoundException.class);

        mockMvc.perform(get("/events/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_status_is_bad_request() throws Exception {
        long id = -1L;

        mockMvc.perform(get("/events/{id}", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_status_is_created() throws Exception {
        var eventForm = Instancio.of(EventPublishingForm.class)
                .withSettings(settings)
                .create();

        var response = new EventWithContent(
                1L,
                eventForm.title(),
                eventForm.content(),
                eventForm.timing(),
                eventForm.capacity(),
                Event.EventStatus.SCHEDULED,
                eventForm.authorId(),
                "Foo Bar"
        );

        when(eventService.save(eventForm)).thenReturn(response);

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventForm)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(response.id()))
                .andExpect(jsonPath("title").value(response.title()))
                .andExpect(jsonPath("content").value(response.content()))
                .andExpect(jsonPath("timing").value(response.timing().toString()))
                .andExpect(jsonPath("capacity").value(response.capacity()))
                .andExpect(jsonPath("status").value(response.status().toString()))
                .andExpect(jsonPath("authorId").value(response.authorId()))
                .andExpect(jsonPath("authorUsername").value(response.authorUsername()))
        ;
    }

    @Test
    void create_status_is_bad_request() throws Exception {
        var eventForm = Instancio.of(EventPublishingForm.class)
                .set(field("title"), " ")
                .withSettings(settings)
                .create();
        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventForm)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_status_is_no_content() throws Exception {
        mockMvc.perform(delete("/events/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_status_is_bad_request() throws Exception {
        mockMvc.perform(delete("/events/{id}", -1L))
                .andExpect(status().isBadRequest());
    }

    @Test
    void delete_status_is_notfound() throws Exception {
        long id = 1L;

        doThrow(EventNotFoundException.class).when(eventService).deleteById(id);

        mockMvc.perform(delete("/events/{id}", id))
                .andExpect(status().isNotFound());
    }
}