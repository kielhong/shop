package com.widehouse.order;

import com.widehouse.Product;
import com.widehouse.order.OrderLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by kiel on 2016. 6. 15..
 */
@RunWith(SpringRunner.class)
public class OrderLineTest {

    @Test
    public void testCalculateTotalPrice() {
        // Given
        Product product = new Product();
        OrderLine orderLine = new OrderLine(product, 500, 2);
        // Then
        assertThat(orderLine.getAmount()).isEqualTo(1000);
    }
}
