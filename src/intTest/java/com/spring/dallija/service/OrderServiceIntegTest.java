package com.spring.dallija.service;

import com.spring.dallija.api.dto.OrderDto;
import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.order.OrderStatus;
import com.spring.dallija.domain.user.GenderStatus;
import com.spring.dallija.domain.user.Health;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.exception.order.NotEnoughStockException;
import com.spring.dallija.repository.OrdersRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceIntegTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrdersRepositoryImpl ordersRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        User user = createUser();
        Item meat = createMeat("소고기 볶음", 10000, 100);

        //when
        OrderDto.SaveOrderRequest saveOrderRequest =
                new OrderDto.SaveOrderRequest(user.getId(), meat.getId(), 3, "을지로", "222222");
        Long orderId = ordersService.saveOrder(saveOrderRequest);

        //then
        Order getOrder = ordersRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태 ORDER");
        assertEquals(1, getOrder.getOrdersItems().size(), "주문한 상품 종류 수가 정확해야된다.");
        assertEquals(10000 * 3, getOrder.getTotalPrice(), "주문 가격 체크 가격 * 수량");
        assertEquals(97, meat.getStockQuantity(), "주문후 재고 수량 일치");
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        User user = createUser();
        Item item = createMeat("소고기 볶음", 10000, 10);

        OrderDto.SaveOrderRequest saveOrderRequest =
                new OrderDto.SaveOrderRequest(user.getId(), item.getId(), 7, "을지로", "222222");

        Long orderId = ordersService.saveOrder(saveOrderRequest);

        //when
        ordersService.cancelOrder(orderId);

        //then
        Order getOrder = ordersRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 Cancel");
        assertEquals(10, item.getStockQuantity(), "주문이 취소된 상품 수 만큼 재고가 증가해야된다.");


    }

    @Test
    public void 상품주문_재고초과() throws Exception {
        //given
        User user = createUser();
        Item item = createMeat("소고기 볶음", 10000, 10);

        //when
        OrderDto.SaveOrderRequest saveOrderRequest =
                new OrderDto.SaveOrderRequest(user.getId(), item.getId(), 11, "을지로", "222222");

        assertThrows(NotEnoughStockException.class, () -> ordersService.saveOrder(saveOrderRequest),
                "재고가 없습니다.");

        //then
        //fail("재고 수량 부족 예외 발생해야 된다.");

    }


    private Item createMeat(String name, int price, int stockQuantity) {
        Item meat = new Item(name, price, stockQuantity, "횡성");
        em.persist(meat);
        return meat;
    }

    private User createUser() {
        User user = new User("min10", "min10@naver.com", "11111111",
                new Address("한강로", "1232-2"),
                new Health(GenderStatus.MAN, 129, 239));
        em.persist(user);
        return user;
    }


}
