package com.widehouse.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by kiel on 2016. 6. 17..
 */
@AllArgsConstructor
@Getter
public class ShippingAddress {
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
}
