package com.example.dao.museum.repositories;

import com.example.dao.museum.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evhen Malysh
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
