package com.widehouse.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.widehouse.Product;
import com.widehouse.exception.OrderNotFoundException;
import com.widehouse.order.domain.Order;
import com.widehouse.order.domain.OrderLine;
import com.widehouse.order.domain.OrderRepository;
import com.widehouse.order.domain.OrderState;
import com.widehouse.order.domain.ShippingAddress;
import com.widehouse.order.domain.ShippingInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kiel on 2016. 6. 17..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @MockBean
    OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    /**
     * set up
     */
    @Before
    public void setup() {
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 1));
        ShippingAddress shippingAddress = new ShippingAddress("", "", "");
        ShippingInfo shippingInfo = new ShippingInfo("", "", shippingAddress);

        given(orderRepository.findOne(1L))
                .willReturn(new Order(orderLines, shippingInfo));
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
    public void cancelNotExistOrderShouldFail() {
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
}
