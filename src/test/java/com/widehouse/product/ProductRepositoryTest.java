package com.widehouse.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kiel on 2016. 6. 20..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByCategory() {
        // Given
        Category category1 = new Category();
        entityManager.persist(category1);
        Category category2 = new Category();
        entityManager.persist(category2);
        Product product1 = new Product("test1", category1);
        Product product2 = new Product("test2", category1);
        Product product3 = new Product("test3", category1);
        Product product4 = new Product("test4", category1);
        entityManager.persist(product1);
        entityManager.persist(product2);
        entityManager.persist(product3);
        entityManager.persist(product4);
        entityManager.persist(new Product("test5", category2));
        entityManager.persist(new Product("test6", category2));
        entityManager.persist(new Product("test7", category2));
        Pageable pageable = new PageRequest(0, 3, new Sort(Sort.Direction.ASC, "id"));
        // When
        List<Product> products = productRepository.findByCategory(category1, pageable);

        // Then
        assertThat(products.size()).isEqualTo(3);
        assertThat(products).isEqualTo(Arrays.asList(product1, product2, product3));
    }

}
