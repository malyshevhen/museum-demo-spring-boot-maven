package com.example.users.repositories;

import com.example.users.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

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
    Optional<User> findByEmail(String email);
}
