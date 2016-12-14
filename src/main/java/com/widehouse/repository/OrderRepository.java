package com.widehouse.repository;


import com.widehouse.domain.order.Order;
import com.widehouse.dto.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by kiel on 2016. 6. 17..
 */
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    @Query("SELECT new com.widehouse.dto.OrderView(o, m) " +
            "FROM Order o INNER JOIN o.orderer m " +
            "WHERE m.memberId.id = :ordererId " +
            "ORDER BY o.id DESC")
    List<OrderView> findByOrdererId(@Param("ordererId") String ordererId);
}
