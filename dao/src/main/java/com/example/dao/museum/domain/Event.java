package com.example.dao.museum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Event is domain model of the museum event business object.
 *
 * @author Evhen Malysh
 */
@Entity
@Table(name = "events", indexes = {
        @Index(name = "idx_event_title", columnList = "title"),
        @Index(name = "idx_event_timing", columnList = "timing")
})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Event {

    // @formatter:on

    /**
     * Unique entity identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Event post title.
     */
    @Column(name = "title", nullable = false)
    @NotNull
    @NotBlank
    private String title;

    /**
     * Body of event web-post.
     */
    @Column(name = "body", nullable = false)
    @NotNull
    @NotBlank
    private String body;

    /**
     * Time when event is starts.
     */
    @Column(name = "timing")
    @NotNull
    private LocalDateTime timing;

    /**
     * Maximum number of the visitors.
     */
    @Column(name = "capacity", nullable = false)
    @NotNull
    @Positive
    private Integer capacity;

    /**
     * Event status.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private EventStatus status;

    /**
     * Author of the web-post.
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
     * @param eventTitle  Event post title.
     * @param eventBody   Body of event web-post.
     * @param eventAuthor Author of the web-post.
     */
    public Event(
            final @NotNull @NotBlank String eventTitle,
            final @NotNull @NotBlank String eventBody,
            final @NotNull Author eventAuthor) {
        this.title = eventTitle;
        this.body = eventBody;
        this.author = eventAuthor;
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
        Event event = (Event) o;
        return getId() != null && Objects.equals(getId(), event.getId());
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
     * Enum representing the status of an event.
     *
     * @author Evhen Malysh
     */
    public enum EventStatus {

        /**
         * Event has been scheduled but not yet active.
         */
        SCHEDULED,

        /**
         * Event is currently active and ongoing.
         */
        ACTIVE,

        /**
         * Event is at full capacity and cannot accommodate more attendees.
         */
        FULL,

        /**
         * Event has been transferred to another time.
         */
        TRANSFERRED,

        /**
         * Event has been canceled and will not take place.
         */
        CANCELED,

        /**
         * Event has been archived and is no longer active.
         */
        ARCHIVED
    }
}
