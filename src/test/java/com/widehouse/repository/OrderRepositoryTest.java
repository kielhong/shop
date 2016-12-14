package com.widehouse.repository;

import static com.widehouse.repository.OrderSpecifications.isOrderByMember;
import static org.assertj.core.api.Assertions.assertThat;

import com.widehouse.domain.member.Member;
import com.widehouse.domain.member.MemberId;
import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.OrderLine;
import com.widehouse.domain.order.Receiver;
import com.widehouse.domain.order.ShippingAddress;
import com.widehouse.domain.order.ShippingInfo;
import com.widehouse.domain.product.Product;
import com.widehouse.dto.OrderView;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

    private Product product;
    private List<OrderLine> orderLines;
    private Receiver receiver;
    private ShippingAddress shippingAddress;
    private ShippingInfo shippingInfo;
    @Before
    public void setUp() {
        product = new Product();
        orderLines = Arrays.asList(new OrderLine(product, 100, 1));
        receiver = new Receiver("tester", "123-456-7890");
        shippingAddress = new ShippingAddress("", "", "Reston", "");
        shippingInfo = new ShippingInfo(receiver, shippingAddress);
    }

    @Test
    public void shouldFindOrdersByOrdererId() {
        // Given
        Member orderer1 = new Member(new MemberId("orderer1"), "orderder1");
        Member orderer2 = new Member(new MemberId("orderer2"), "orderder2");
        entityManager.persist(orderer1);
        entityManager.persist(orderer2);
        entityManager.persist(new Order(orderLines, orderer1, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer1, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer2, LocalDateTime.now(), shippingInfo));

        // When
        List<Order> results = repository.findAll(isOrderByMember(orderer1));
        // Then
        assertThat(results.size()).isEqualTo(2);
        results.stream().allMatch(x -> x.getOrderer().equals(orderer1));
    }

    @Test
    public void shouldFindOrderViewsByOrdererId() {
        // Given
        String orderedId1 = "orderer1";
        Member orderer1 = new Member(new MemberId(orderedId1), "orderder1");
        Member orderer2 = new Member(new MemberId("orderer2"), "orderder2");
        entityManager.persist(orderer1);
        entityManager.persist(orderer2);
        entityManager.persist(new Order(orderLines, orderer1, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer1, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer2, LocalDateTime.now(), shippingInfo));

        // When
        List<OrderView> results = repository.findByOrdererId(orderedId1);
        // Then
        assertThat(results.size()).isEqualTo(2);
        results.stream().allMatch(x -> x.getOrdererId().equals(orderedId1));
    }

    @Test
    public void shouldFindBetweenDate() {
        // Given
        Member orderer = new Member(new MemberId("orderer1"), "orderder1");
        entityManager.persist(orderer);
        entityManager.persist(new Order(orderLines, orderer, LocalDateTime.now(), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer, LocalDateTime.now().minusDays(1), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer, LocalDateTime.now().minusDays(2), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer, LocalDateTime.now().minusDays(3), shippingInfo));
        entityManager.persist(new Order(orderLines, orderer, LocalDateTime.now().minusDays(10), shippingInfo));
        // When
        LocalDate now = LocalDate.now();
        LocalDateTime to = now.atTime(23, 59, 59);
        LocalDateTime from = now.minusDays(2).atTime(0, 0, 0);
        List<Order> results = repository.findAll(OrderSpecifications.between(from, to));
        // Then
        assertThat(results.size()).isEqualTo(3);
    }
}
