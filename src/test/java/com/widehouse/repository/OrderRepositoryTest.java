package com.widehouse.repository;

import static com.widehouse.repository.OrderSpecifications.between;
import static com.widehouse.repository.OrderSpecifications.isOrderByMember;
import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.OrderLine;
import com.widehouse.domain.order.Receiver;
import com.widehouse.domain.order.ShippingAddress;
import com.widehouse.domain.order.ShippingInfo;
import com.widehouse.domain.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kiel on 2016. 12. 12..
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class OrderRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private OrderRepository repository;

    @Test
    public void shouldFindOrderByOrdererId() {
        // Given
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 1));
        String ordererId1 = "orderer";
        String ordererId2 = "orderer2";
        Receiver receiver = new Receiver("tester", "123-456-7890");
        ShippingAddress shippingAddress = new ShippingAddress("", "", "Reston", "");
        ShippingInfo shippingInfo = new ShippingInfo(receiver, shippingAddress);
        entityManager.persist(new Order(orderLines, ordererId1, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, ordererId1, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, ordererId2, LocalDateTime.now(), shippingInfo));

        // When
        List<Order> results = repository.findAll(isOrderByMember(ordererId1));
        // Then
        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    public void shouldFindBetweenDate() {
        // Given
        Product product = new Product();
        List<OrderLine> orderLines = Arrays.asList(new OrderLine(product, 100, 1));
        String ordererId = "orderer";
        Receiver receiver = new Receiver("tester", "123-456-7890");
        ShippingAddress shippingAddress = new ShippingAddress("", "", "Reston", "");
        ShippingInfo shippingInfo = new ShippingInfo(receiver, shippingAddress);
        entityManager.persist(new Order(orderLines, ordererId, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, ordererId, LocalDateTime.now().minusDays(1), shippingInfo));
        entityManager.persist(new Order(orderLines, ordererId, LocalDateTime.now().minusDays(2), shippingInfo));
        entityManager.persist(new Order(orderLines, ordererId, LocalDateTime.now().minusDays(3), shippingInfo));
        entityManager.persist(new Order(orderLines, ordererId, LocalDateTime.now().minusDays(10), shippingInfo));
        // When
        LocalDate now = LocalDate.now();
        LocalDateTime to = now.atTime(23, 59, 59);
        LocalDateTime from = now.minusDays(2).atTime(0, 0, 0);
        List<Order> results = repository.findAll(OrderSpecifications.between(from, to));
        // Then
        assertThat(results.size()).isEqualTo(3);
    }
}
