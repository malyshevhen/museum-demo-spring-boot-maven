// @formatter:off

package com.example.gallery.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * ArtWork is domain model of the piece of art business object.
 *
 * @author Evhen Malysh
 */
@Entity
@Table(
    name = "artworks", indexes = {
        @Index(name = "idx_artworks_artwork_name", columnList = "artwork_name"),
        @Index(name = "idx_artworks_artist_id", columnList = "artist_id")
}, uniqueConstraints = {
        @UniqueConstraint(
            name = "uc_artworks_artwork_name",
            columnNames = {"artwork_name"})
})
@NoArgsConstructor
@Getter @Setter @ToString
public class Artwork {

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
     * Name of the piece of art.
     */
    @Column(name = "artwork_name", nullable = false, unique = true)
    @NotNull(message = "Name is mandatory")
    @NotBlank(message = "Name is mandatory")
    @Size(max = MAX_NAME_LENGTH, message = "name is longer than 50 characters")
    private String name;

    /**
     * Artist of the piece of art.
     */
    @Valid
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    /**
     * Price of artwork id USD.
     */
    @Column(name = "price", nullable = false)
    @NotNull
    @Positive
    private Double price;

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
     * @param artworkName      name of the piece of art
     * @param artworkArtist    Artist object related to this artwork
     */
    public Artwork(final @NotNull @NotBlank String artworkName,
                   final @NotNull Artist artworkArtist) {
        this.name = artworkName;
        this.artist = artworkArtist;
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
        Artwork artWork = (Artwork) o;
        return getId() != null && Objects.equals(getId(), artWork.getId());
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
