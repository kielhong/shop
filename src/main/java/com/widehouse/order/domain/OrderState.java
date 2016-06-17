package com.widehouse.order.domain;

/**
 * Created by kiel on 2016. 6. 16..
 */
public enum OrderState {
    PAYMENT_WAITING,
    PREPARING,
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED;
}
