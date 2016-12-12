package com.widehouse.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by kiel on 2016. 6. 17..
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ShippingAddress {
    private String address1;
    private String address2;
    private String city;
    @Column(length = 10)
    private String zipcode;

}
