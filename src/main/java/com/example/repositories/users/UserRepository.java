package com.example.repositories.users;


import com.example.domain.users.User;
import com.example.dto.users.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Evhen Malysh
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
            SELECT new com.example.dto.users.UserResponse
            (
                u.id,
                u.firstName,
                u.lastName,
                u.email
            )
            FROM User u
            """)
    List<UserResponse> findAllDtos();

    @Query("""
            SELECT new com.example.dto.users.UserResponse
            (
                u.id,
                u.firstName,
                u.lastName,
                u.email
            )
            FROM User u
            WHERE u.id = :id
            """)
    Optional<UserResponse> findDtoById(Long id);

    boolean existsByEmail(String email);
}
