package com.example.web.users;

import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserResponse;
import com.example.services.users.UserService;
import com.example.services.users.exceptions.UserNotFoundException;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@ExtendWith(InstancioExtension.class)
class UserControllerTest {

    @WithSettings
    private static final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true)
            .lock();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void getAll_status_ok() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_status_not_found() throws Exception {
        when(userService.getAll()).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/users"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_status_is_ok() throws Exception {
        var email = "valid@email.com";
        var userResponse = Instancio.of(UserResponse.class)
                .withSettings(settings)
                .set(field("email"), email)
                .create();

        when(userService.getById(userResponse.id())).thenReturn(userResponse);

        mockMvc.perform(get("/users/{id}", userResponse.id()))
                .andExpect(status().isOk())
                        .andExpect(jsonPath("id").value(userResponse.id()))
                        .andExpect(jsonPath("firstName").value(userResponse.firstName()))
                        .andExpect(jsonPath("lastName").value(userResponse.lastName()))
                        .andExpect(jsonPath("email").value(email))
        ;
    }

    @Test
    void getById_status_not_found() throws Exception {
        long id = 1L;
        when(userService.getById(id)).thenThrow(UserNotFoundException.class);

        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isNotFound());
    }

    @Test
    void getById_status_bad_request() throws Exception {
        long id = -1L;

        mockMvc.perform(get("/users/{id}", id))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_status_is_created() throws Exception {
        var email = "valid@email.com";
        var password = "ValidPassword1";

        var userForm = Instancio.of(UserRegistrationForm.class)
                .withSettings(settings)
                .set(field("email"), email)
                .set(field("password"), password)
                .create();

        var userResponse = new UserResponse(1L, userForm.firstName(), userForm.lastName(), email);

        when(userService.save(userForm)).thenReturn(userResponse);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userForm)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(userResponse.id()))
                .andExpect(jsonPath("firstName").value(userResponse.firstName()))
                .andExpect(jsonPath("lastName").value(userResponse.lastName()))
                .andExpect(jsonPath("email").value(email))
        ;
    }

    @Test
    void create_status_is_bad_request() throws Exception {
        var email = "invalid_email.com";
        var password = "invalidPassword";

        var userForm = Instancio.of(UserRegistrationForm.class)
                .withSettings(settings)
                .set(field("email"), email)
                .set(field("password"), password)
                .create();

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userForm)))
                .andExpect(status().isBadRequest());
    }
}