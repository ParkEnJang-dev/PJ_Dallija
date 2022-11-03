package com.spring.dallija.service;

import com.spring.dallija.domain.order.Order;
import com.spring.dallija.repository.order.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.spring.dallija.controller.dto.OrderDto.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
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

     @Test
     public void 유저_상품_주문() throws Exception {
         //given
         SaveOrderRequest saveOrderRequest
                 = new SaveOrderRequest(3L,12L,1,"한강로","2222");
         Order order = ordersService.saveOrder(saveOrderRequest);

         //when
         Order findOrder = ordersService.findById(order.getId());

         //then
         assertThat(order.getId()).isEqualTo(findOrder.getId());
      }
}
