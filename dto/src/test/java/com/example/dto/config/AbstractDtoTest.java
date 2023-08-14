package com.example.dto.config;

import com.example.dto.museum.author.AuthorRegistrationForm;
import com.example.dto.museum.author.AuthorShortResponse;
import com.example.dto.users.UserRegistrationForm;
import com.example.dto.users.UserShortResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

import static org.instancio.Select.field;

@ExtendWith(InstancioExtension.class)
public class AbstractDtoTest {
    private Validator validator;

    @BeforeEach
    void init() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @WithSettings
    private static final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true)
            .lock();

    protected <T> Set<ConstraintViolation<T>> validate(T object) {
        return validator.validate(object);
    }

    protected UserRegistrationForm getUserRegistrationForm() {
        return Instancio.of(getUserFormModel())
                .withSettings(settings)
                .create();
    }

    protected UserRegistrationForm getUserRegistrationForm(String fieldName, String value) {
        return Instancio.of(getUserFormModel(fieldName, value))
                .withSettings(settings)
                .create();
    }

    protected UserShortResponse getUserShortResponse(String fieldName, String value) {
        return Instancio.of(UserShortResponse.class)
                .set(field(fieldName), value)
                .withSettings(settings)
                .create();
    }

    protected UserShortResponse getUserShortResponse(Long idValue) {
        return Instancio.of(UserShortResponse.class)
                .set(field("id"), idValue)
                .withSettings(settings)
                .create();
    }

    protected Model<UserRegistrationForm> getUserFormModel() {
        return Instancio.of(UserRegistrationForm.class)
                .set(field("password"), "ValidPassword1")
                .set(field("email"), "valid@gmail.com")
                .withSettings(settings)
                .toModel();
    }

    protected Model<UserRegistrationForm> getUserFormModel(String fieldName, String value) {
        return Instancio.of(UserRegistrationForm.class)
                .set(field("password"), "ValidPassword1")
                .set(field("email"), "valid@gmail.com")
                .set(field(fieldName), value)
                .withSettings(settings)
                .toModel();
    }

    protected AuthorRegistrationForm getAuthorRegistrationForm(String value) {
        return Instancio.of(AuthorRegistrationForm.class)
                .set(field("username"), value)
                .withSettings(settings)
                .create();
    }

    protected AuthorRegistrationForm getAuthorRegistrationForm(Long value) {
        return Instancio.of(AuthorRegistrationForm.class)
                .set(field("userId"), value)
                .withSettings(settings)
                .create();
    }

    protected AuthorShortResponse getAuthorShortResponse(String fieldName, String value) {
        return Instancio.of(AuthorShortResponse.class)
                .set(field(fieldName), value)
                .withSettings(settings)
                .create();
    }
    protected AuthorShortResponse getAuthorShortResponse(Long value) {
        return Instancio.of(AuthorShortResponse.class)
                .set(field("id"), value)
                .withSettings(settings)
                .create();
    }
}
