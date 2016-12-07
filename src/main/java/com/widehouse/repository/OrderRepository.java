package com.widehouse.repository;


import com.widehouse.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kiel on 2016. 6. 17..
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
