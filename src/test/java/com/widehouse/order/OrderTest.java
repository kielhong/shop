package com.widehouse.order;


import com.widehouse.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kiel on 2016. 6. 15..
 */
@RunWith(SpringRunner.class)
public class OrderTest {

    @Test
    public void testSetOrderLinesShouldSetTotalAmounts() {
        // Given
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 2), new OrderLine(product, 300, 1));
        ShippingInfo shippingInfo = new ShippingInfo("", "", "", "", "");
        // When
        Order order = new Order(orderLines, shippingInfo);
        // Then
        assertThat(order.getTotalAmount()).isEqualTo(500);
    }

    @Test
    public void emptyOrderLineShouldNotEmpty() {
        // Give
        List<OrderLine> orderLines = new ArrayList<>();
        ShippingInfo shippingInfo = new ShippingInfo("", "", "", "", "");

        // When
        try {
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

}
