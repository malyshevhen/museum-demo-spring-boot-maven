package com.example.museum.repositories;

import com.example.museum.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
