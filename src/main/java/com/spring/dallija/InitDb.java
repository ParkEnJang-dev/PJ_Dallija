package com.spring.dallija;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.delivery.Delivery;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.order.OrderItem;
import com.spring.dallija.domain.user.GenderStatus;
import com.spring.dallija.domain.user.Health;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit() {
            User user = createUser("Ab", "ABC@naver.com");
            em.persist(user);
            User adminUser = createAdminUser("admin","admin@gmail.com");
            em.persist(adminUser);

        }

        public void dbInit1() {

            User user = createUser("A", "A@naver.com");
            em.persist(user);

            Item item1 = createItem("소고기 볶음");
            em.persist(item1);

            Item item2 = createItem("소고기 구이");
            em.persist(item2);
            Delivery delivery = createDelivery("한강");

            List<OrderItem> orderItems1 = new ArrayList<>();
            OrderItem orderItem = OrderItem.createOrderItem(item1, 10000, 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(item2, 10000, 4);
            orderItems1.add(orderItem);
            orderItems1.add(orderItem2);
            Order order = Order.createOrder(user, delivery, orderItems1);
            em.persist(order);

            List<OrderItem> orderItems2 = new ArrayList<>();
            OrderItem orderItem3 = OrderItem.createOrderItem(item1, 5000, 2);
            OrderItem orderItem4 = OrderItem.createOrderItem(item2, 5000, 4);
            orderItems2.add(orderItem3);
            orderItems2.add(orderItem4);
            Order order2 = Order.createOrder(user, delivery, orderItems2);
            em.persist(order2);


        }

        public void dbInit2() {

            User user = createUser("B", "B@naver.com");
            em.persist(user);

            Item item1 = createItem("양배추 볶음");
            em.persist(item1);

            Item item2 = createItem("양배추 튀김");
            em.persist(item2);

            OrderItem orderItem = OrderItem.createOrderItem(item1, 10000, 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(item2, 10000, 4);
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            orderItems.add(orderItem2);

            Delivery delivery = createDelivery("부산");
            Order order = Order.createOrder(user, delivery, orderItems);

            em.persist(order);

        }

        public void dbInit3() {
            for (int i =1 ; i <= 100 ; i++){
                Item item1 = createItem("양배추 볶음"+i);
                em.persist(item1);
            }
        }




        private Delivery createDelivery(String street) {
            return new Delivery(new Address(street, "1212"));
        }

        private Item createItem(String name) {
            return new Item(name,10000, 50, "횡성");
        }

        private User createUser(String name, String email) {
            return new User(name, email, "11111111",
                    new Address("한강로", "11111111"),
                    new Health(GenderStatus.MAN, 180, 49));
        }

        private User createAdminUser(String name, String email) {
            return new User(name, email, "11111111", UserRole.ADMIN,
                    new Address("한강로", "11111111"),
                    new Health(GenderStatus.WOMAN, 180, 49));
        }
    }
}
