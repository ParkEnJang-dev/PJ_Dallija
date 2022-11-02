package com.spring.dallija.service;

import com.spring.dallija.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.dallija.controller.dto.OrderDto.OrderCond;
import static com.spring.dallija.controller.dto.OrderDto.OrderUserResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional(readOnly = true)
@Slf4j
public class OrderServiceIntegTest {

    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderRepository ordersRepository;

    @Test
    public void 유저_주문_조회() throws Exception {
        //given
        OrderCond orderCond = new OrderCond(4L);
        PageRequest request = PageRequest.of(0, 10);
        Page<OrderUserResponse> orders = ordersService.findOrderUser(orderCond, request);

        //when
        for (OrderUserResponse orderUserResponse : orders) {
            System.out.println(orderUserResponse);
        }

     }

}
