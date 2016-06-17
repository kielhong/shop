package com.widehouse.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by kiel on 2016. 6. 16..
 */
@AllArgsConstructor
@Getter
public class ShippingInfo {
    private String receiverName;
    private String receiverPhoneNumber;
    private ShippingAddress address;
}
