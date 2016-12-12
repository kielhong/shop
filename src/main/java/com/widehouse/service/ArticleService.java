package com.widehouse.service;

import com.widehouse.domain.review.Article;
import com.widehouse.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kiel on 2016. 12. 12..
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article write(Article article) {
        return articleRepository.save(article);
    }
}
