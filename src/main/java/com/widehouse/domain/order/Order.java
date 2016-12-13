package com.widehouse.domain.order;

import com.widehouse.domain.member.MemberId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Created by kiel on 2016. 6. 15..
 */
@Entity
@Table(name = "purchase_order")
@Getter
@EqualsAndHashCode(of = "id")
@ToString
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "order_line")
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;

    @NotNull
    @AttributeOverride(name = "memberId", column = @Column(name = "orderer_id", length = 20))
    private MemberId ordererId;

    @NotNull
    private LocalDateTime orderDate;

    @NotNull
    private ShippingInfo shippingInfo;

    private Long totalAmounts;

    /**
     * Constructor
     * @param orderLines 주문 항목 목록
     * @param shippingInfo 배송 정보
     */
    public Order(List<OrderLine> orderLines, String ordererId, LocalDateTime orderDate, ShippingInfo shippingInfo) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        this.ordererId = new MemberId(ordererId);
        this.orderDate = orderDate;
        this.orderState = OrderState.PREPARING;
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
