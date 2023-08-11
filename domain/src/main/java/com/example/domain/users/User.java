package com.example.domain.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import static com.example.constraints.domain.UserConstraints.*;

/**
 * Entity class representing a user in the application.
 *
 * @author Evhen Malysh
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

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
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    @NotNull
    @NotBlank
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "last_name", nullable = false)
    @Size(min = MIN_NAME_LENGTH, max = MAX_NAME_LENGTH)
    @NotNull
    @NotBlank
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(name = "email", nullable = false)
    @Email(regexp = EMAIL_REGEXP)
    @NotNull
    @NotBlank
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false)
    @NotNull
    @Pattern(regexp = PASSWORD_REGEXP)
    @Size(max = PASSWORD_MAX)
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

    public User(
            final @NotNull @NotBlank String firstName,
            final @NotNull @NotBlank String lastName,
            final @NotNull @Email String email,
            final @NotNull @Pattern(regexp = PASSWORD_REGEXP) String password,
            Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
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
