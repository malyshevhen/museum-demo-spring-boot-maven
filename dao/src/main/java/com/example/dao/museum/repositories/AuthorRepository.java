package com.example.dao.museum.repositories;

import com.example.dao.museum.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @author Evhen Malysh
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * @param userId ID of parent user account
     * @return Optional of {@link Author} by user ID
     */
    @Query("select a from Author a where a.user.id = ?1")
    Optional<Author> findByUserId(Long userId);
}
