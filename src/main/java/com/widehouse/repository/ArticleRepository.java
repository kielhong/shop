package com.widehouse.repository;

import com.widehouse.domain.review.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kiel on 2016. 12. 12..
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
