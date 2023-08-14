package com.example.utils;

import com.example.domain.museum.Article;
import com.example.domain.museum.Author;
import com.example.domain.museum.Event;
import com.example.domain.users.User;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.settings.Keys;
import org.instancio.settings.Settings;

import static org.instancio.Select.field;

/**
 * Utility class for generating instancio models
 *
 * @author Evhen Malysh
 */
public class FakeModelFactory {

    private static final Settings settings = Settings.create()
            .set(Keys.BEAN_VALIDATION_ENABLED, true)
            .lock();

    public static User getValidUser() {
        return Instancio.of(getUserModel())
                .withSettings(settings)
                .create();
    }

    public static Author getValidAuthor() {
        return Instancio.of(getAuthorModel())
                .withSettings(settings)
                .create();
    }

    public static Article getValidArticle() {
        return Instancio.of(getArticleModel())
                .withSettings(settings)
                .create();
    }

    public static Event getValidEvent() {
        return Instancio.of(getEventModel())
                .withSettings(settings)
                .create();
    }

    private static Model<User> getUserModel() {
        return Instancio.of(User.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("roles"))
                .set(field("password"), "ValidPassword1")
                .set(field("email"), "valid@gmail.com")
                .toModel();
    }

    private static Model<Author> getAuthorModel() {
        var user = Instancio.of(getUserModel()).create();

        return Instancio.of(Author.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("events"))
                .set(field("user"), user)
                .toModel();
    }

    private static Model<Article> getArticleModel() {
        var author = Instancio.of(getAuthorModel()).create();

        return Instancio.of(Article.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("tags"))
                .set(field("author"), author)
                .toModel();
    }

    private static Model<Event> getEventModel() {
        var author = Instancio.of(getAuthorModel()).create();

        return Instancio.of(Event.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .set(field("author"), author)
                .toModel();
    }
}
