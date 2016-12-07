package com.widehouse.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * Created by kiel on 2016. 6. 16..
 */
@Embeddable
@AllArgsConstructor
@Getter
@ToString
public class ShippingInfo {
    private String receiverName;
    private String receiverPhoneNumber;

    private ShippingAddress address;
}
