package com.widehouse.order;

import com.widehouse.Product;

/**
 * Created by kiel on 2016. 6. 15..
 */
public class OrderLine {
    private Product product;
    private long price;
    private int quantity;
    private long amount;

    public OrderLine(Product product, long price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amount = price * quantity;
    }

    public long getAmount() {
        return amount;
    }
}
