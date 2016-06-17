package com.widehouse.exception;

/**
 * Created by kiel on 2016. 6. 17..
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long orderId) {
        super(orderId + " Not Found");
    }
}
