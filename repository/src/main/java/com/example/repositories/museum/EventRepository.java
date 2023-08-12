package com.example.repositories.museum;

import com.example.domain.museum.Event;
import com.example.dto.museum.event.EventWithContent;
import com.example.dto.museum.event.EventWithoutContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Evhen Malysh
 */
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("""
            SELECT new com.example.dto.museum.event.EventWithoutContent(
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
    List<EventWithoutContent> findAllEventsWithoutContent();

    @Query("""
            SELECT new com.example.dto.museum.event.EventWithContent(
                e.id,
                e.title,
                e.content,
                e.timing,
                e.capacity,
                e.status,
                e.author.id,
                e.author.username
            )
            FROM Event e
            WHERE e.id = :id
            """)
    Optional<EventWithContent> findEventWithContentById(Long id);
}
