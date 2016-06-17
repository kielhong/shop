package com.widehouse.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.product.Product;
import org.junit.Test;


/**
 * Created by kiel on 2016. 6. 15..
 */
//@RunWith(SpringRunner.class)
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
