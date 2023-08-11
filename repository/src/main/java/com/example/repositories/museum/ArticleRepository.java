package com.example.repositories.museum;

import com.example.domain.museum.Article;
import com.example.domain.museum.Article.ArticleTag;
import com.example.dto.museum.article.ArticleWithBody;
import com.example.dto.museum.article.ArticleWithoutBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Evhen Malysh
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    record ArticleDbRowWithBody(
            Long id,
            String title,
            String body,
            Long authorId,
            String authorUsername,
            LocalDateTime createdAt) {


    }
    @Query("""
            SELECT new com.example.repositories.museum.ArticleRepository$ArticleDbRowWithBody(
                a.id,
                a.title,
                a.body,
                a.author.id,
                a.author.username,
                a.createdAt)
            FROM Article a
            WHERE a.author.id = :authorId
            ORDER BY a.id
            """)
    List<ArticleDbRowWithBody> loadAllArticlesDataWithBodyByAuthorId(Long authorId);


    @Query("""
            SELECT a.tags
            FROM Article a
            WHERE a.id = ?1
            """)
    Set<ArticleTag> loadArticleTagsById(Long id);

    default List<ArticleWithBody> findAllWithBodyByAuthorId(Long authorId) {
        var articlesData = loadAllArticlesDataWithBodyByAuthorId(authorId);
        var resultList = new ArrayList<ArticleWithBody>();

        articlesData.forEach(a -> {
            var tags = loadArticleTagsById(a.id);
            var articleTagWithBody = new ArticleWithBody(
                    a.id,
                    a.title,
                    a.body,
                    tags,
                    a.authorId,
                    a.authorUsername,
                    a.createdAt
            );
            resultList.add(articleTagWithBody);
        });
        return resultList;
    }

    record ArticleDbRowWithoutBody(
            Long id,
            String title,
            Long authorId,
            String authorUsername,
            LocalDateTime createdAt) {


    }
    @Query("""
            SELECT new com.example.repositories.museum.ArticleRepository$ArticleDbRowWithoutBody(
                a.id,
                a.title,
                a.author.id,
                a.author.username,
                a.createdAt)
            FROM Article a
            ORDER BY a.id
            """)
    List<ArticleDbRowWithoutBody> loadAllArticleDataWithoutBody();

    default List<ArticleWithoutBody> findAllWithoutBody() {
        var articlesData = loadAllArticleDataWithoutBody();
        var resultList = new ArrayList<ArticleWithoutBody>();

        articlesData.forEach(a -> {
            var tags = loadArticleTagsById(a.id);
            var articleTagWithoutBody = new ArticleWithoutBody(
                    a.id,
                    a.title,
                    tags,
                    a.authorId,
                    a.authorUsername,
                    a.createdAt
            );
            resultList.add(articleTagWithoutBody);
        });
        return resultList;
    }

    @Query("""
            SELECT new com.example.repositories.museum.ArticleRepository$ArticleDbRowWithBody(
                a.id,
                a.title,
                a.body,
                a.author.id,
                a.author.username,
                a.createdAt)
            FROM Article a
            WHERE a.id = :id
            ORDER BY a.id
            """)
    Optional<ArticleDbRowWithBody> loadArticleDataWithBodyById(Long id);

    default Optional<ArticleWithBody> findArticleWithBodyById(Long id) {
        var article = loadArticleDataWithBodyById(id);
        var tags = loadArticleTagsById(id);

        return article.map(a -> new ArticleWithBody(
                a.id,
                a.title,
                a.body,
                tags,
                a.authorId,
                a.authorUsername,
                a.createdAt
        ));
    }

    @Transactional
    @Modifying
    @Query("update Article a set a.title = :title, a.body = :body where a.id = :id")
    void updateTitleAndBodyById(String title, String body, Long id);
}
