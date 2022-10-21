package com.spring.dallija.repository;


import com.spring.dallija.api.dto.OrderDto;
import com.spring.dallija.api.dto.SimpleOrdersDto;
import com.spring.dallija.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrdersRepositoryImpl {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    //임시. 제작
    public List<Order> findAllByString(OrderDto.OrderSearch orderSearch) {
        String jpql = "select o From Order o join o.user u";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getUserName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getUserName())) {
            query = query.setParameter("name", orderSearch.getUserName());
        }
        return query.getResultList();
    }

    public List<Order> findAllWithUserDeliver() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.user m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();

    }

    public List<SimpleOrdersDto> findOrderDtos() {
        return em.createQuery(
                "select new com.spring.dallija.api.dto.SimpleOrdersDto(o.id, u.name, o.orderTime, o.status, d.address)" +
                        " from Order o" +
                        " join o.user u" +
                        " join o.delivery d", SimpleOrdersDto.class
        ).getResultList();
    }
}
