package com.widehouse.order.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kiel on 2016. 6. 17..
 */
public interface OrderRepository {
    Order findOne(Long orderId);
}
