package com.widehouse.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * Created by kiel on 2016. 12. 8..
 */
@Embeddable
@AllArgsConstructor
@Getter
public class ProductId implements Serializable {
    private Long id;
}
