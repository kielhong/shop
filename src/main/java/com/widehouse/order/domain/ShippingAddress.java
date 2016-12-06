package com.widehouse.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * Created by kiel on 2016. 6. 17..
 */
@Embeddable
@AllArgsConstructor
@Getter
public class ShippingAddress {
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingCity;
    private String shippingZipcode;

}
