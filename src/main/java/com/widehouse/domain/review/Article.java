package com.widehouse.domain.review;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

/**
 * Created by kiel on 2016. 12. 7..
 */
@Entity
@SecondaryTable(
        name = "article_content",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class Article {
    @Id
    private Long id;

    @Column(length = 500)
    private String title;

    @Column(table = "article_content", length = 10)
    private String contentType;

    @Column(table = "article_content", columnDefinition = "text")
    private String content;

}
