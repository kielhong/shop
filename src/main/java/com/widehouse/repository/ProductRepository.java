package com.widehouse.repository;

import com.widehouse.domain.product.Category;
import com.widehouse.domain.product.Product;
import com.widehouse.domain.product.ProductId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kiel on 2016. 6. 20..
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE :category MEMBER OF p.categories")
    List<Product> findByCategory(@Param("category") Category category, Pageable pageable);
}
