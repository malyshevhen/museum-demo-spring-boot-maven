package com.example.repositories.museum;

import com.example.domain.museum.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
