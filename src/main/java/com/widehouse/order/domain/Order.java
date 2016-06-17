package com.widehouse.order.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by kiel on 2016. 6. 15..
 */
@Getter
@EqualsAndHashCode(of = "id")
public class Order {
    private Long id;
    @NotNull
    private OrderState orderState;
    @NotNull
    private List<OrderLine> orderLines;
    @NotNull
    private ShippingInfo shippingInfo;
    private long totalAmount;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        orderState = OrderState.PREPARING;
    }

    public void changeShippingInfo(ShippingInfo shippingInfo) {
        if (isNotYetShipped()) {
            setShippingInfo(shippingInfo);
        } else {
            throw new IllegalArgumentException("already shipped");
        }
    }

    public void changeOrderState(OrderState newState) {
        this.orderState = newState;
    }

    public void cancel() {
        if (isNotYetShipped()) {
            changeOrderState(OrderState.CANCELED);
        } else {
            throw new IllegalArgumentException("already shipped");
        }
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        Assert.notEmpty(orderLines);

        this.orderLines = orderLines;

        this.totalAmount = orderLines.stream()
                .mapToLong(OrderLine::getAmount)
                .sum();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        Assert.notNull(shippingInfo);

        this.shippingInfo = shippingInfo;
    }

    private boolean isNotYetShipped() {
        return (orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING);
    }


}
