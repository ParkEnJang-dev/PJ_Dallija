package com.spring.dallija.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
    private final EntityManager em;

    //고객 트래픽이 정말 많이 들어올경우 .
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new com.spring.dallija.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate , o.status , d.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
