package com.widehouse.service;

import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.ShippingInfo;
import com.widehouse.exception.OrderNotFoundException;
import com.widehouse.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kiel on 2016. 6. 17..
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order findOrder(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    /**
     * 주문을 취소한다.
     * @param orderId 취소하려는 주문 id
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = findOrder(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        order.cancel();
    }

    /**
     * 배송 주소 변경
     * @param orderId 변경하려는 Order id
     * @param newShippingInfo 변경하려는 배송 주소
     */
    @Transactional
    public void changeShippingInfo(Long orderId, ShippingInfo newShippingInfo) {
        Order order = orderRepository.findOne(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        order.changeShippingInfo(newShippingInfo);
        orderRepository.saveAndFlush(order);
    }

}
