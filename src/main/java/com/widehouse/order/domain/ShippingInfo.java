package com.widehouse.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Created by kiel on 2016. 6. 16..
 */
@Embeddable
@AllArgsConstructor
@Getter
public class ShippingInfo {
    private String receiverName;
    private String receiverPhoneNumber;

    private ShippingAddress address;
}
