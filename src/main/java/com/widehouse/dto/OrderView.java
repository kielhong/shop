package com.widehouse.dto;

import com.widehouse.domain.member.Member;
import com.widehouse.domain.order.Order;
import lombok.Getter;

/**
 * Created by kiel on 2016. 12. 14..
 */
@Getter
public class OrderView {
    private Long id;
    private String ordererId;
    private String ordererName;
    private Long totalAmounts;

    public OrderView(Order order, Member member) {
        this.id = order.getId();
        this.ordererId = member.getMemberId().getId();
        this.ordererName = member.getName();
        this.totalAmounts = order.getTotalAmounts();
    }
}
