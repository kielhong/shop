package com.widehouse;

import lombok.Getter;

import java.util.List;

/**
 * Created by kiel on 2016. 6. 15..
 */
public class Order {
    private List<OrderLine> orderLines;
    @Getter
    private long totalAmount;

    public Order(List<OrderLine> orderLines) {
        this.orderLines = orderLines;

        this.totalAmount = orderLines.stream()
                .mapToLong(OrderLine::getAmount)
                .sum();
    }


}
