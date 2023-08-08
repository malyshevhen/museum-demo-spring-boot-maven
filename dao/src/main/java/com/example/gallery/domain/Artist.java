// @formatter:off

package com.example.gallery.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Artist is domain model of the artist business object.
 *
 * @author Evhen Malysh
 */
@Entity
@Table(
    name = "artists",
    indexes = {
        @Index(name = "idx_artists_first_name", columnList = "first_name"),
        @Index(name = "idx_artists_last_name", columnList = "last_name")
})
@NoArgsConstructor
@Getter @Setter @ToString
public class Artist {

    // @formatter:on

    /**
     * Provided limit of the name length.
     */
    private static final int MAX_NAME_LENGTH = 50;

    /**
     * Unique entity identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Artist`s first name.
     */
    @Column(name = "first_name", nullable = false)
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(max = MAX_NAME_LENGTH, message = "name is longer than 50 characters")
    private String firstName;

    /**
     * Artist`s last name.
     */
    @Column(name = "last_name", nullable = false)
    @NotNull(message = "First name is mandatory")
    @NotBlank(message = "Last name is mandatory")
    @Size(max = MAX_NAME_LENGTH, message = "name is longer than 50 characters")
    private String lastName;

    /**
     * Set of unique author`s artworks.
     */
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "artist", orphanRemoval = true)
    private Set<Artwork> artWorks = new LinkedHashSet<>();

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
     * @param authorFirstName first name of the author
     * @param authorLastName  last name of the author
     */
    public Artist(final @NotNull @NotBlank String authorFirstName,
                  final @NotNull @NotBlank String authorLastName) {
        this.firstName = authorFirstName;
        this.lastName = authorLastName;
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
        Artist artist = (Artist) o;
        return getId() != null && Objects.equals(getId(), artist.getId());
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
