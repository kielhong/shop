package com.widehouse.order.domain;

import com.widehouse.product.Product;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by kiel on 2016. 6. 15..
 */
@Embeddable
public class OrderLine {
    @ManyToOne
    private Product product;
    private long price;
    private int quantity;
    private long amounts;

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
        this.amounts = calcuateAmounts();
    }

    public long getAmounts() {
        return amounts;
    }

    private long calcuateAmounts() {
        return price * quantity;
    }
}
