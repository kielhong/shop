package com.widehouse.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

/**
 * Created by kiel on 2016. 12. 8..
 */
@Embeddable
@AllArgsConstructor
public class ProductId implements Serializable {
    private Long id;
}
