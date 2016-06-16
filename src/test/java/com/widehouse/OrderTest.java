package com.widehouse;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

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
        // When
        Order order = new Order(orderLines);
        // Then
        assertThat(order.getTotalAmount()).isEqualTo(500);
    }


}
