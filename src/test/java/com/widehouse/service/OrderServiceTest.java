package com.widehouse.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.widehouse.domain.member.Member;
import com.widehouse.domain.member.MemberId;
import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.OrderLine;
import com.widehouse.domain.order.OrderState;
import com.widehouse.domain.order.Receiver;
import com.widehouse.domain.order.ShippingAddress;
import com.widehouse.domain.order.ShippingInfo;
import com.widehouse.domain.product.Product;
import com.widehouse.exception.OrderNotFoundException;
import com.widehouse.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kiel on 2016. 6. 17..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @MockBean
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    /**
     * set up
     */
    @Before
    public void setup() {
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 1));
        Member orderer = new Member(new MemberId("orderer"), "username");
        Receiver receiver = new Receiver("tester", "123-456-7890");
        ShippingAddress shippingAddress = new ShippingAddress("", "", "Reston", "");
        ShippingInfo shippingInfo = new ShippingInfo(receiver, shippingAddress);

        given(this.orderRepository.findOne(1L))
                .willReturn(new Order(orderLines, orderer, LocalDateTime.now(), shippingInfo));
        given(this.orderRepository.findOne(0L))
                .willReturn(new Order(orderLines, orderer, LocalDateTime.now(), shippingInfo));
    }

    @Test
    public void cancelOrderShouldChangeOrderStateToCancel() {
        // Given
        Long orderId = 1L;
        // When
        orderService.cancelOrder(orderId);
        // Then
        Order order = orderService.findOrder(orderId);
        assertThat(order.getOrderState()).isEqualTo(OrderState.CANCELED);
    }

    @Test
    public void cancelNotExistOrderShouldThrowException() {
        // Given
        Long orderId = 0L;
        try {
            // When
            orderService.cancelOrder(orderId);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(OrderNotFoundException.class);
        }
    }

    @Test
    public void changeShippingInfoShouldChangeShippingInfo() {
        // Given
        Long orderId = 1L;
        Receiver newReceiver = new Receiver("newuser", "123-456-7890");
        ShippingAddress newShippingAddress = new ShippingAddress("", "", "Herndon", "");
        ShippingInfo newShippingInfo = new ShippingInfo(newReceiver, newShippingAddress);

        // When
        orderService.changeShippingInfo(orderId, newShippingInfo);

        // Then
        Order changedOrder = orderRepository.findOne(orderId);
        assertThat(changedOrder.getShippingInfo()).isEqualTo(newShippingInfo);
    }

    @Test
    public void changeShippingInfoOfNotExistOrderShoudThrowException() {
        // Given
        Long orderId = -1L;
        Receiver newReceiver = new Receiver("newuser", "123-456-7890");
        ShippingAddress newShippingAddress = new ShippingAddress("", "", "Herndon", "");
        ShippingInfo newShippingInfo = new ShippingInfo(newReceiver, newShippingAddress);

        try {
            // When
            orderService.changeShippingInfo(orderId, newShippingInfo);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(OrderNotFoundException.class);
        }

    }
}
