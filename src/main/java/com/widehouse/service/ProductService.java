package com.widehouse.service;

import com.widehouse.domain.product.Category;
import com.widehouse.domain.product.Product;
import com.widehouse.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by kiel on 2016. 12. 8..
 */
@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /**
     * Product 를 생성한다
     * @param name product name
     * @param price product price
     * @param categories categories of product
     * @return created Product
     */
    @Transactional
    public Product createProduct(String name, Integer price, Set<Category> categories) {
        Product product = new Product(name, price, categories);

        return productRepository.save(product);
    }
}
