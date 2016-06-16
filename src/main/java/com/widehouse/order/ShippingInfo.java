package com.widehouse.order;

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
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
}
