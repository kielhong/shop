package com.widehouse.order.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import javax.validation.constraints.NotNull;

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

    /**
     * Constructor
     * @param orderLines 주문 항목 목록
     * @param shippingInfo 배송 정보
     */
    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        orderState = OrderState.PREPARING;
    }

    /**
     * 배송 정보를 변경한다.
     * @param shippingInfo 변경될 배송 정보
     */
    public void changeShippingInfo(ShippingInfo shippingInfo) {
        if (isNotYetShipped()) {
            setShippingInfo(shippingInfo);
        } else {
            throw new IllegalArgumentException("already shipped");
        }
    }

    /**
     * 주문 상태값을 변경한다.
     * @param newOrderState 새로운 주문 상태값
     */
    public void changeOrderState(OrderState newOrderState) {
        this.orderState = newOrderState;
    }

    /**
     * 주문을 취소한다.
     */
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
