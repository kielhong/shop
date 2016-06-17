package com.widehouse.order.domain;

import com.widehouse.Product;

/**
 * Created by kiel on 2016. 6. 15..
 */
public class OrderLine {
    private Product product;
    private long price;
    private int quantity;
    private long amount;

    /**
     * Constructors
     * @param product 주문 대상 상품
     * @param price 주문 가격
     * @param quantity 상품 개수
     */
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
