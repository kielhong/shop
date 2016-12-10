package com.widehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

import com.widehouse.domain.order.Order;
import com.widehouse.domain.product.Category;
import com.widehouse.domain.product.Product;
import com.widehouse.domain.product.ProductId;
import com.widehouse.repository.OrderRepository;
import com.widehouse.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kiel on 2016. 12. 8..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceTest {
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Before
    public void setUp() {
    }

    @Test
    public void shouldCreateProduct() {
        // Given
        String name = "Test Product";
        Category category = new Category();
        given(this.productRepository.save(any(Product.class)))
                .willReturn(new Product(1L, name, category));
        // When
        Product product = productService.createProduct(name, category);
        // Then
        assertThat(product).isNotNull();
    }

    @Configuration
    @Import(ProductService.class)
    static class Config {}
}