package com.example.gallery.repositories;

import com.example.gallery.domain.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
