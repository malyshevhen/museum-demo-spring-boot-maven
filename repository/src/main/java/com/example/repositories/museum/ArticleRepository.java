package com.example.repositories.museum;

import com.example.domain.museum.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
