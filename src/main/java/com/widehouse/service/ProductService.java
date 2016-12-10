package com.widehouse.service;

import com.widehouse.domain.product.Category;
import com.widehouse.domain.product.Product;
import com.widehouse.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kiel on 2016. 12. 8..
 */
@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product createProduct(String name, Category category) {
        Product product = new Product(name, category);

        return productRepository.save(product);
    }
}
