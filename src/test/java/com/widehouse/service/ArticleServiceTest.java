package com.widehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import com.widehouse.domain.review.Article;
import com.widehouse.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kiel on 2016. 12. 12..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {
    @MockBean
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

    @Test
    public void shoudCreateArticle() {
        // Given
        given(this.articleRepository.save(any(Article.class)))
                .willReturn(new Article(1L, "title", "type", "content"));
        // Then
        Article article = articleService.write(new Article("title", "type", "content"));
        //
        assertThat(article).isNotNull();
        assertThat(article.getId()).isNotNull();

    }
}
