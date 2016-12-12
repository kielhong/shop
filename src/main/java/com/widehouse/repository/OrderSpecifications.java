package com.widehouse.repository;

import com.widehouse.domain.member.MemberId;
import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.Order_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by kiel on 2016. 12. 12..
 */
public class OrderSpecifications {
    public static Specification<Order> isOrderByMember(String ordererId) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(Order_.ordererId), new MemberId(ordererId));
            }
        };
    }

}
