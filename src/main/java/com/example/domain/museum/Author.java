package com.example.domain.museum;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.validation.annotation.Validated;

import static com.example.constraints.users.UserConstraints.MAX_NAME_LENGTH;
import static com.example.constraints.users.UserConstraints.MIN_NAME_LENGTH;
import com.example.domain.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Author is domain model of the author business object.
 *
 * @author Evhen Malysh
 */
@Entity
@Validated
@Table(name = "authors",
        indexes = {@Index(name = "idx_author_username", columnList = "username")},
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_author_user_id", columnNames = {"user_id"})
        })
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {

    /**
     * Unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Nickname of the author.
     */
    @NotNull
    @NotBlank
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    @Column(name = "username")
    private String username;

    /**
     * User account relative to author.
     */
    @OneToOne
    @JoinColumn(name = "user_id",nullable = false, unique = true)
    @NotNull
    @Valid
    private User user;

    /**
     * All authors posted events.
     */
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Set<Event> events = new LinkedHashSet<>();

    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Set<Article> articles = new LinkedHashSet<>();
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
     * @param authorsUsername    username of the author
     * @param authorsUserAccount user account relative to author
     */
    public Author(final @NotNull @NotBlank String authorsUsername,
                  final @NotNull User authorsUserAccount) {
        this.username = authorsUsername;
        this.user = authorsUserAccount;
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
        Author author = (Author) o;
        return getId() != null && Objects.equals(getId(), author.getId());
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
}
