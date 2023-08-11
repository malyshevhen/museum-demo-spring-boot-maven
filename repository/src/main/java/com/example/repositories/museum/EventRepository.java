package com.example.repositories.museum;

import com.example.domain.museum.Event;
import com.example.dto.museum.event.EventWithBody;
import com.example.dto.museum.event.EventWithoutBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Evhen Malysh
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
            SELECT new com.example.dto.museum.event.EventWithoutBody(
                e.id,
                e.title,
                e.timing,
                e.capacity,
                e.status,
                e.author.id,
                e.author.username
            )
            FROM Event e
            """)
    List<EventWithoutBody> findAllEventsWithoutBody();

    @Query("""
            SELECT new com.example.dto.museum.event.EventWithBody(
                e.id,
                e.title,
                e.body,
                e.timing,
                e.capacity,
                e.status,
                e.author.id,
                e.author.username
            )
            FROM Event e
            WHERE e.id = :id
            """)
    Optional<EventWithBody> findEventWithBodyById(Long id);
}
