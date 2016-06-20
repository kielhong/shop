package com.widehouse.category;

import com.widehouse.product.Product;
import lombok.Getter;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kiel on 2016. 6. 20..
 */
@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue
    private Long id;
}
