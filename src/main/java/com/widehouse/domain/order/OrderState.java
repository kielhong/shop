package com.widehouse.domain.order;

/**
 * Created by kiel on 2016. 6. 16..
 */
public enum OrderState {
    PAYMENT_WAITING {
        @Override
        public boolean isShippingChangeable() {
            return true;
        }
    },
    PREPARING {
        @Override
        public boolean isShippingChangeable() {
            return true;
        }
    },
    SHIPPED,
    DELIVERING,
    DELIVERY_COMPLETED,
    CANCELED;


    public boolean isShippingChangeable() {
        return false;
    }
}
