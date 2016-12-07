package com.widehouse.domain.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by kiel on 2016. 6. 15..
 */
@Entity
@Table(name = "purchase_order")
@Getter
@EqualsAndHashCode(of = "id")
public class Order {
    @Id
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "order_line")
    private List<OrderLine> orderLines;

    @NotNull
    private ShippingInfo shippingInfo;

    private Long totalAmounts;

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
        verifyNotYetShipped();
        setShippingInfo(shippingInfo);
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
        verifyNotYetShipped();
        changeOrderState(OrderState.CANCELED);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        Assert.notEmpty(orderLines);

        this.orderLines = orderLines;

        this.totalAmounts = orderLines.stream()
                .mapToLong(OrderLine::getAmounts)
                .sum();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        Assert.notNull(shippingInfo);

        this.shippingInfo = shippingInfo;
    }

    private boolean shippingChangeable() {
        return (orderState == OrderState.PAYMENT_WAITING || orderState == OrderState.PREPARING);
    }

    private void verifyNotYetShipped() {
        if (orderState != OrderState.PAYMENT_WAITING && orderState != OrderState.PREPARING) {
            throw new IllegalStateException("already shipped");
        }
    }


}
