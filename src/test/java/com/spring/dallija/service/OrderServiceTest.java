package com.spring.dallija.service;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.Member;
import com.spring.dallija.domain.Order;
import com.spring.dallija.domain.OrderStatus;
import com.spring.dallija.domain.item.Book;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.exception.NotEnoughStockException;
import com.spring.dallija.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("시골 jpa", 10000, 10);

        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(getOrder.getStatus(), OrderStatus.ORDER, "상품 주문시 상태는 ORDER");
        assertEquals(getOrder.getOrderItems().size(), 1, "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(getOrder.getTotalPrice(), 10000 * orderCount,  "주문 가격은 가격 * 수량이다.");
        assertEquals(book.getStockQuantity(), 8,  "주문 수량만큼 재고가 줄어야 한다.");
    }


    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 jpa", 10000, 10);

        int orderCount = 11;
        //when


        NotEnoughStockException thrown= assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(), item.getId(), orderCount));

        //then
        assertEquals("재고가 없습니다.",thrown.getMessage());
        //then
        //fail("재고 수량 부족 예외가 발생해야 한다.");

     }

     @Test
     public void 주문취소() throws Exception {
         //given
         Member member = createMember();
         Book item = createBook("시골 jpa", 10000, 10);

         int orderCount = 2;

         Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

         //when
         orderService.cancelOrder(orderId);

         //then
         Order getOrder = orderRepository.findOne(orderId);

         assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 CANCEL 이다");
         assertEquals(10,item.getStockQuantity(),"주문이 취소된 상품은 그만큼 재고가 증가해야 한다.");
     }




    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "한강로", "2323-222"));
        em.persist(member);
        return member;
    }



}