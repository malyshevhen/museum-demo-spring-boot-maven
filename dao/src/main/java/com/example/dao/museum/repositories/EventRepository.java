package com.example.dao.museum.repositories;

import com.example.dao.museum.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface EventRepository extends JpaRepository<Event, Long> {
}
