// @formatter:off

package com.example.users.domain;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class representing a user in the application.
 *
 * @author Evhen Malysh
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter @Setter @ToString
public class User {

    // @formatter:on

    /**
     * This regular expression enforces that the password must
     * have at least one alphabetic character and at least one digit,
     * and it must be at least 8 characters long.
     */
    private static final String PASSWORD_REGEXP
            = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    /**
     * Minimum length of the password.
     */
    private static final int PASSWORD_MIN = 8;

    /**
     * Maximum length of the password.
     */
    private static final int PASSWORD_MAX = 25;

    /**
     * Unique entity identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The first name of the user.
     */
    @Column(name = "first_name", nullable = false)
    @NotNull
    @NotBlank
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "last_name", nullable = false)
    @NotNull
    @NotBlank
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(name = "email", nullable = false)
    @Email
    @NotNull
    @NotBlank
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false)
    @NotNull
    @Pattern(regexp = PASSWORD_REGEXP)
    @Size(min = PASSWORD_MIN, max = PASSWORD_MAX)
    private String password;

    /**
     * Users address.
     */
    @Embedded
    private Address address;

    /**
     * The roles associated with the user.
     */
    @Setter(AccessLevel.PRIVATE)
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new LinkedHashSet<>();

    /**
     * The timestamp when the user was created.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * The timestamp when the user was last updated.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

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
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
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
     * Enum representing different roles in the application.
     *
     * @author Evhen Malysh
     */
    public enum Role {

        /**
         * Standard user role.
         */
        USER,

        /**
         * Role for artists.
         */
        ARTIST,

        /**
         * Role for authors.
         */
        AUTHOR,

        /**
         * Administrator role with full access.
         */
        ADMIN
    }
}
