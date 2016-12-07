package com.widehouse.domain.product;

import lombok.Getter;

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
