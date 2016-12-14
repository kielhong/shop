package com.widehouse.repository;

import com.widehouse.domain.member.Member;
import com.widehouse.domain.member.MemberId;
import com.widehouse.domain.member.MemberId_;
import com.widehouse.domain.member.Member_;
import com.widehouse.domain.order.Order;
import com.widehouse.domain.order.Order_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by kiel on 2016. 12. 12..
 */
public class OrderSpecifications {
    /**
     * 주문자가 orderId 인지 여부
     * @param orderer 주문자
     * @return Specification
     */
    public static Specification<Order> isOrderByMember(Member orderer) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get(Order_.orderer), orderer);
            }
        };
    }

    /**
     * 주문 날짜가 from 에서 to 사이인 spec
     * @param from 범위 시작 LocalDateTime
     * @param to 범위 끝 LocalDateTime
     * @return Specification
     */
    public static Specification<Order> between(LocalDateTime from, LocalDateTime to) {
        return new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.between(root.get(Order_.orderDate), from, to);
            }
        };
    }
}
