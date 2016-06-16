package com.widehouse.order;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by kiel on 2016. 6. 15..
 */
@Getter
public class Order {
    private OrderState orderState;
    private List<OrderLine> orderLines;
    private long totalAmount;
    private ShippingInfo shippingInfo;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        orderState = OrderState.PAYMENT_WAITING;

        this.totalAmount = orderLines.stream()
                .mapToLong(OrderLine::getAmount)
                .sum();
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        if (isNotYetShipped()) {
            setShippingInfo(shippingInfo);
        } else {
            throw new IllegalArgumentException("after shipped, shipping info can not change");
        }
    }

    public void changeOrderState(OrderState newState) {
        this.orderState = newState;
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        Assert.notEmpty(orderLines);

        this.orderLines = orderLines;
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        Assert.notNull(shippingInfo);

        this.shippingInfo = shippingInfo;
    }

    private boolean isNotYetShipped() {
        return (orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING);
    }


}
