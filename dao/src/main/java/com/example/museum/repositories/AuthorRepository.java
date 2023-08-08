package com.example.museum.repositories;

import com.example.museum.domain.Author;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * @param userId ID of parent user account
     * @return Optional of {@link Author} by user ID
     */
    Optional<Author> findByUserId(Long userId);
}
