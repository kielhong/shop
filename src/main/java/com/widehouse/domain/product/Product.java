package com.widehouse.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Created by kiel on 2016. 6. 15..
 */
@Entity
@Getter
@ToString
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer price;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"))
    private Set<Category> categories;

    public Product() {}

    public Product(Long id, String name, Integer price, Set<Category> categories) {
        this(name, price, categories);
        this.id = id;
    }

    /**
     * Constructor without id and one category
     * @param name name of product
     * @param price price of product
     * @param category category of product
     */
    public Product(String name, Integer price, Category category) {
        this.name = name;
        this.price = price;
        Set<Category> categories = new HashSet<>();
        categories.add(category);
        addCategory(categories);
    }

    /**
     * Constructor without id and many categories
     * @param name name of product
     * @param price price of product
     * @param categories categories of product
     */
    public Product(String name, Integer price, Set<Category> categories) {
        this.name = name;
        this.price = price;
        addCategory(categories);
    }

    private void addCategory(Set<Category> categories) {
        if (this.categories == null) {
            this.categories = new HashSet<>();
        }
        this.categories.addAll(categories);
    }
}
