package com.example.repositories.users;

import com.example.domain.users.User;
import com.example.dto.users.UserShortResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Evhen Malysh
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email.
     *
     * @param email email of user
     * @return Optional of user with given id
     */
    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("""
            SELECT com.example.dto.users.UserShortResponse(
                u.firstName,
                u.lastName,
                u.email
            )
            FROM User u
            """)
    List<UserShortResponse> findAllDtos();

    @Query("""
            SELECT com.example.dto.users.UserShortResponse(
                u.firstName,
                u.lastName,
                u.email
            )
            FROM User u
            WHERE u.id = :id
            """)
    Optional<UserShortResponse> findDtoById(Long id);

    boolean existsByEmail(String email);
}
