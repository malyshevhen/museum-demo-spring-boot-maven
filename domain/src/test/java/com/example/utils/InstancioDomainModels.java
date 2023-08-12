package com.example.utils;

import com.example.constraints.domain.museum.ArticleConstraints;
import com.example.constraints.domain.museum.EventConstraints;
import com.example.domain.museum.Article;
import com.example.domain.museum.Author;
import com.example.domain.museum.Event;
import com.example.domain.users.User;
import org.instancio.Instancio;
import org.instancio.Model;

import static com.example.constraints.domain.users.UserConstraints.MAX_NAME_LENGTH;
import static com.example.constraints.domain.users.UserConstraints.MIN_NAME_LENGTH;
import static org.instancio.Select.field;

/**
 * Utility class for generating instancio models
 *
 * @author Evhen Malysh
 */
public class InstancioDomainModels {

    public static Model<User> getUserModel() {
        return Instancio.of(User.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("roles"))
                .generate(field("firstName"),
                        gen -> gen.string()
                                .minLength(MIN_NAME_LENGTH)
                                .maxLength(MAX_NAME_LENGTH)
                                .mixedCase())
                .generate(field("lastName"),
                        gen -> gen.string()
                                .minLength(MIN_NAME_LENGTH)
                                .maxLength(MAX_NAME_LENGTH)
                                .mixedCase())
                .set(field("password"), "ValidPassword1")
                .set(field("email"), "valid@gmail.com")
                .toModel();
    }
    public static Model<Author> getAuthorModel() {
        var user = Instancio.of(getUserModel()).create();

        return Instancio.of(Author.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("events"))
                .generate(field("username"),
                        gen -> gen.string()
                                .minLength(MIN_NAME_LENGTH)
                                .maxLength(MAX_NAME_LENGTH)
                                .mixedCase())
                .set(field("user"), user)
                .toModel();
    }

    public static Model<Article> getArticleModel() {
        var author = Instancio.of(getAuthorModel()).create();

        return Instancio.of(Article.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .ignore(field("tags"))
                .set(field("author"), author)
                .generate(field("title"), gen -> gen.string()
                        .minLength(ArticleConstraints.MIN_TITLE_LENGTH)
                        .maxLength(ArticleConstraints.MAX_TITLE_LENGTH)
                        .mixedCase())
                .generate(field("content"), gen -> gen.string()
                        .minLength(ArticleConstraints.MIN_CONTENT_LENGTH)
                        .maxLength(ArticleConstraints.MAX_CONTENT_LENGTH)
                        .mixedCase())
                .toModel();
    }

    public static Model<Event> getEventModel() {
        var author = Instancio.of(getAuthorModel()).create();

        return Instancio.of(Event.class)
                .ignore(field("id"))
                .ignore(field("createdAt"))
                .ignore(field("updatedAt"))
                .set(field("author"), author)
                .generate(field("title"), gen -> gen.string()
                        .minLength(EventConstraints.MIN_TITLE_LENGTH)
                        .maxLength(EventConstraints.MAX_TITLE_LENGTH)
                        .mixedCase())
                .generate(field("content"), gen -> gen.string()
                        .minLength(EventConstraints.MIN_CONTENT_LENGTH)
                        .maxLength(EventConstraints.MAX_CONTENT_LENGTH)
                        .mixedCase())
                .toModel();
    }
}
