package com.example.repositories.museum;


import com.example.domain.museum.Author;
import com.example.dto.museum.author.AuthorShortResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Evhen Malysh
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Retrieve all authors.
     *
     * @return AuthorShortResponse list of all authors.
     */
    @Query("""
            SELECT new com.example.dto.museum.author.AuthorShortResponse
            (
                a.id,
                a.username,
                a.user.firstName,
                a.user.lastName
            )
            FROM Author a
            """)
    List<AuthorShortResponse> findAllAuthors();

    /**
     * Retrieve an author by ID.
     *
     * @param id authors ID.
     * @return AuthorShortResponse by given ID.
     */
    @Query("""
            SELECT new com.example.dto.museum.author.AuthorShortResponse
            (
                a.id,
                a.username,
                a.user.firstName,
                a.user.lastName
            )
            FROM Author a
            WHERE a.id = :id
            """)
    Optional<AuthorShortResponse> findAuthorById(Long id);

    /**
     * Update username of the user with given ID.
     *
     * @param username username to update.
     * @param id ID of the author to update.
     */
    @Transactional
    @Modifying
    @Query("update Author a set a.username = ?1 where a.id = ?2")
    void updateUsernameById(String username, Long id);

    /**
     * Check is user with given username is existing in DB.
     *
     * @param username username of the author.
     * @return true if user with the given username is existing or false if not.
     */
    boolean existsByUsername(String username);
}
