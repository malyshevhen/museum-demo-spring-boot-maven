package com.example.repositories.museum;

import com.example.domain.museum.Article;
import com.example.domain.museum.Article.ArticleTag;
import com.example.dto.museum.article.ArticleWithContent;
import com.example.dto.museum.article.ArticleWithoutContent;
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

    record ArticleDbRowWithContent(
            Long id,
            String title,
            String content,
            Long authorId,
            String authorUsername,
            LocalDateTime createdAt) {


    }
    @Query("""
            SELECT new com.example.repositories.museum.ArticleRepository$ArticleDbRowWithContent
            (
                a.id,
                a.title,
                a.content,
                a.author.id,
                a.author.username,
                a.createdAt)
            FROM Article a
            WHERE a.author.id = :authorId
            ORDER BY a.id
            """)
    List<ArticleDbRowWithContent> loadAllArticlesDataWithContentByAuthorId(Long authorId);


    @Query("""
            SELECT a.tags
            FROM Article a
            WHERE a.id = ?1
            """)
    Set<ArticleTag> loadArticleTagsById(Long id);

    default List<ArticleWithContent> findAllWithBodyByAuthorId(Long authorId) {
        var articlesData = loadAllArticlesDataWithContentByAuthorId(authorId);
        var resultList = new ArrayList<ArticleWithContent>();

        articlesData.forEach(a -> {
            var tags = loadArticleTagsById(a.id);
            var articleTagWithBody = new ArticleWithContent(
                    a.id,
                    a.title,
                    a.content,
                    tags,
                    a.authorId,
                    a.authorUsername,
                    a.createdAt
            );
            resultList.add(articleTagWithBody);
        });
        return resultList;
    }

    record ArticleDbRowWithoutContent(
            Long id,
            String title,
            Long authorId,
            String authorUsername,
            LocalDateTime createdAt) {


    }
    @Query("""
            SELECT new com.example.repositories.museum.ArticleRepository$ArticleDbRowWithoutContent
            (
                a.id,
                a.title,
                a.author.id,
                a.author.username,
                a.createdAt)
            FROM Article a
            ORDER BY a.id
            """)
    List<ArticleDbRowWithoutContent> loadAllArticleDataWithoutContent();

    default List<ArticleWithoutContent> findAllWithoutContent() {
        var articlesData = loadAllArticleDataWithoutContent();
        var resultList = new ArrayList<ArticleWithoutContent>();

        articlesData.forEach(a -> {
            var tags = loadArticleTagsById(a.id);
            var articleTagWithoutBody = new ArticleWithoutContent(
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
            SELECT new com.example.repositories.museum.ArticleRepository$ArticleDbRowWithContent
            (
                a.id,
                a.title,
                a.content,
                a.author.id,
                a.author.username,
                a.createdAt)
            FROM Article a
            WHERE a.id = :id
            ORDER BY a.id
            """)
    Optional<ArticleDbRowWithContent> loadArticleDataWithContentById(Long id);

    default Optional<ArticleWithContent> findArticleWithContentById(Long id) {
        var article = loadArticleDataWithContentById(id);
        var tags = loadArticleTagsById(id);

        return article.map(a -> new ArticleWithContent(
                a.id,
                a.title,
                a.content,
                tags,
                a.authorId,
                a.authorUsername,
                a.createdAt
        ));
    }

    @Transactional
    @Modifying
    @Query("update Article a set a.title = :title, a.content = :content where a.id = :id")
    void updateTitleAndBodyById(String title, String content, Long id);
}
