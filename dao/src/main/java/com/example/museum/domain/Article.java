// @formatter:off

package com.example.museum.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Article is domain model of the web-page article business object.
 *
 * @author Evhen Malysh
 */
@Entity
@Table(name = "articles")
@RequiredArgsConstructor
@Getter @Setter @ToString
public class Article {

    // @formatter:on

    /**
     * Unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Title string of article.
     */
    @Column(name = "title", nullable = false)
    @NotNull
    @NotBlank
    private String title;

    /**
     * Body of article.
     */
    @Column(name = "body", nullable = false)
    @NotNull
    @NotBlank
    private String body;

    /**
     * Collection of #tags related to the article.
     */
    @Setter(AccessLevel.PRIVATE)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<WebArticleTag> tags = new LinkedHashSet<>();

    /**
     * Author of the article.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /**
     * Timestamp of record creation.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp of record update.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * @param articleTitle  Title string of article
     * @param articleBody   Body of article
     * @param articleTags   Collection of #tags related to the article
     * @param articleAuthor Author of the article
     */
    public Article(
            @NotNull @NotBlank final String articleTitle,
            @NotNull @NotBlank final String articleBody,
            final Set<WebArticleTag> articleTags,
            final Author articleAuthor) {
        this.title = articleTitle;
        this.body = articleBody;
        this.tags = articleTags;
        this.author = articleAuthor;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        Class<?> oEffectiveClass;
        if (o instanceof HibernateProxy hibernateProxy) {
            oEffectiveClass = hibernateProxy
                    .getHibernateLazyInitializer()
                    .getPersistentClass();
        } else {
            oEffectiveClass = o.getClass();
        }
        Class<?> thisEffectiveClass;
        if (this instanceof HibernateProxy hibernateProxy) {
            thisEffectiveClass = hibernateProxy
                    .getHibernateLazyInitializer()
                    .getPersistentClass();
        } else {
            thisEffectiveClass = this.getClass();
        }
        if (thisEffectiveClass != oEffectiveClass) {
            return false;
        }
        Article article = (Article) o;
        return getId() != null && Objects.equals(getId(), article.getId());
    }

    @Override
    public final int hashCode() {
        if (this instanceof HibernateProxy hibernateProxy) {
            return hibernateProxy
                    .getHibernateLazyInitializer()
                    .getPersistentClass()
                    .hashCode();
        }
        return getClass().hashCode();
    }

    /**
     * Enum representing the tags for web articles on a museum webpage.
     *
     * @author Evhen Malysh
     */
    public enum WebArticleTag {

        /**
         * Tag for articles related to art history and cultural heritage.
         */
        ART_HISTORY,

        /**
         * Tag for articles about current exhibitions and displays.
         */
        EXHIBITIONS,

        /**
         * Tag for articles discussing museum events and workshops.
         */
        EVENTS,

        /**
         * Tag for articles focusing on archaeological discoveries.
         */
        ARCHAEOLOGY,

        /**
         * Tag for articles featuring interviews with artists and curators.
         */
        INTERVIEWS,

        /**
         * Tag for articles exploring behind-the-scenes aspects of the museum.
         */
        BEHIND_THE_SCENES
    }
}
