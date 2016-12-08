package com.widehouse.domain.order;


import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.OrderLine;
import com.widehouse.domain.order.OrderState;
import com.widehouse.domain.order.ShippingAddress;
import com.widehouse.domain.order.ShippingInfo;
import com.widehouse.domain.product.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kiel on 2016. 6. 15..
 */
//@RunWith(SpringRunner.class)
public class OrderTest {

    Order order;
    ShippingAddress shippingAddress;

    /**
     * set up
     */
    @Before
    public void setup() {
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 1));
        Receiver receiver = new Receiver("", "");
        shippingAddress = new ShippingAddress("", "", "Reston", "");
        ShippingInfo shippingInfo = new ShippingInfo(receiver, shippingAddress);

        order = new Order(orderLines, shippingInfo);
    }

    @Test
    public void testSetOrderLinesShouldSetTotalAmounts() {
        // Given
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 2), new OrderLine(product, 300, 1));
        Receiver receiver = new Receiver("", "");
        ShippingInfo shippingInfo = new ShippingInfo(receiver, shippingAddress);
        // When
        Order order = new Order(orderLines, shippingInfo);
        // Then
        assertThat(order.getTotalAmounts()).isEqualTo(500);
    }

    @Test
    public void emptyOrderLineShouldNotEmpty() {
        // Give
        List<OrderLine> orderLines = new ArrayList<>();
        Receiver receiver = new Receiver("", "");
        ShippingInfo shippingInfo = new ShippingInfo(receiver, shippingAddress);

        try {
            // When
            Order order = new Order(orderLines, shippingInfo);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void emptyShippingInfoShouldNotNull() {
        // Given
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 1));

        try {
            // When
            Order order = new Order(orderLines, null);
        } catch (Exception e) {
            // Then
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void changeShippingInfoShouldSuccess() {
        // Given
        Receiver receiver = new Receiver("1", "1");
        ShippingInfo newShippingInfo = new ShippingInfo(receiver, shippingAddress);
        order.changeOrderState(OrderState.PREPARING);
        // When
        order.changeShippingInfo(newShippingInfo);
        // Then
        assertThat(order.getShippingInfo()).isEqualTo(newShippingInfo);
    }

    @Test
    public void changeShippingInfoAfterShippedShouldFail() {
        // Given
        order.changeOrderState(OrderState.SHIPPED);

        try {
            // When
            Receiver receiver = new Receiver("1", "1");
            ShippingInfo newShippingInfo = new ShippingInfo(receiver, shippingAddress);
            order.changeShippingInfo(newShippingInfo);
        } catch (Exception e) {
            // Then
            assertThat(e)
                    .isInstanceOf(IllegalStateException.class);
        }
    }

    @Test
    public void cancelAfterShippedShouldFail() {
        // Given
        order.changeOrderState(OrderState.SHIPPED);

        try {
            // When
            order.cancel();
        } catch (Exception e) {
            // Then
            assertThat(e)
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("already shipped");
        }
    }


}
