package com.example.dao.users.repositories;

import com.example.dao.users.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
