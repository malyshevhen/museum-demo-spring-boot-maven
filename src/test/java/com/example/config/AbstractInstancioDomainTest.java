package com.example.config;

import com.example.domain.museum.Article;
import com.example.domain.museum.Author;
import com.example.domain.museum.Event;
import com.example.domain.users.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.instancio.Instancio;
import org.instancio.junit.InstancioExtension;
import org.instancio.junit.WithSettings;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Set;

import static org.instancio.Select.field;

@ExtendWith(InstancioExtension.class)
public abstract class AbstractInstancioDomainTest {

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

    @SuppressWarnings("unchecked")
    protected static <T> T getEntity(Class<T> clazz) {
        if (clazz.equals(User.class)) return (T) getUser();
        if (clazz.equals(Author.class)) return (T) getAuthor();
        if (clazz.equals(Article.class)) return (T) getArticle();
        if (clazz.equals(Event.class)) return (T) getEvent();
        throw new IllegalArgumentException("Method not implemented for provided class.");
    }

    private static User getUser() {
        return Instancio.of(User.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("roles"))
                .set(field("password"), "ValidPassword1")
                .set(field("email"), "valid@gmail.com")
                .withSettings(settings)
                .create();
    }

    private static Author getAuthor() {
        var user = getUser();

        return Instancio.of(Author.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("events"))
                .set(field("user"), user)
                .withSettings(settings)
                .create();
    }

    private static Article getArticle() {
        var author = getAuthor();

        return Instancio.of(Article.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("tags"))
                .set(field("author"), author)
                .withSettings(settings)
                .create();
    }

    private static Event getEvent() {
        var author = getAuthor();

        return Instancio.of(Event.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .set(field("author"), author)
                .withSettings(settings)
                .create();
    }
}
