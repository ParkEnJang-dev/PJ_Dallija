package com.spring.dallija;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.Delivery;
import com.spring.dallija.domain.item.Meat;
import com.spring.dallija.domain.item.Vegetable;
import com.spring.dallija.domain.order.Orders;
import com.spring.dallija.domain.order.OrdersItems;
import com.spring.dallija.domain.user.GenderStatus;
import com.spring.dallija.domain.user.Health;
import com.spring.dallija.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit();
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit(){
            User user = createUser("Ab", "ABC@naver.com");
            em.persist(user);

        }

        public void dbInit1() {

            User user = createUser("A", "A@naver.com");
            em.persist(user);

            Meat meat = createMeat("소고기 볶음");
            em.persist(meat);

            Meat meat2 = createMeat("소고기 구이");
            em.persist(meat2);

            OrdersItems ordersItems = OrdersItems.createOrdersItem(meat, 10000, 2);
            OrdersItems ordersItems2 = OrdersItems.createOrdersItem(meat2, 10000, 4);

            Delivery delivery = createDelivery("한강");
            Orders order = Orders.createOrder(user, delivery, ordersItems, ordersItems2);

            em.persist(order);

        }

        public void dbInit2() {

            User user = createUser("B", "B@naver.com");
            em.persist(user);

            Vegetable vegetable = createVegetable("양배추 볶음");
            em.persist(vegetable);

            Vegetable vegetable2 = createVegetable("양배추 튀김");
            em.persist(vegetable2);

            OrdersItems ordersItems = OrdersItems.createOrdersItem(vegetable, 5000, 2);
            OrdersItems ordersItems2 = OrdersItems.createOrdersItem(vegetable2, 5000, 4);

            Delivery delivery = createDelivery("부산");
            Orders order = Orders.createOrder(user, delivery, ordersItems, ordersItems2);

            em.persist(order);

        }


        private Delivery createDelivery(String street) {
            return new Delivery(new Address(street, "1212"));
        }

        private Meat createMeat(String name) {
            return new Meat(name, 10000, 100, "횡성", LocalDateTime.now(), "BEEF");
        }

        private Vegetable createVegetable(String name) {
            return new Vegetable(name, 5000, 11, "서울", LocalDateTime.now(), "CABBAGE");
        }

        private User createUser(String name, String email) {
            return new User(name, email, "1111",
                    new Address("한강로", "11111111"),
                    new Health(180, 49, GenderStatus.MAN));
        }
    }
}
