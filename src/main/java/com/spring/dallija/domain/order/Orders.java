package com.spring.dallija.domain.order;

import com.spring.dallija.domain.Delivery;
import com.spring.dallija.domain.DeliveryStatus;
import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "order_seq")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "orders")
    private List<OrdersItems> ordersItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderItem(OrdersItems ordersItem) {
        this.ordersItems.add(ordersItem);
        ordersItem.addOrder(this);
    }

    public void addDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.addOrders(this);
    }

    public Orders(User user, LocalDateTime orderTime, OrderStatus status, List<OrdersItems> ordersItems, Delivery delivery) {
        this.user = user;
        this.orderTime = orderTime;
        this.status = status;
        this.ordersItems = ordersItems;
        this.delivery = delivery;
    }

    public static Orders createOrder(User user, Delivery delivery, OrdersItems... ordersItems) {
        Orders orders = new Orders(
                user,
                LocalDateTime.now(),
                OrderStatus.ORDER,
                List.of(ordersItems),
                delivery
        );
        return orders;
    }

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getDeliveryStatus() == DeliveryStatus.SHIPPING) {
            throw new IllegalStateException("배송중인 상품은 취소가 불가능합니다.");
        }

        this.status = OrderStatus.CANCEL;
        this.ordersItems.forEach(OrdersItems::cancel);
    }

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return this.ordersItems.stream()
                .mapToInt(OrdersItems::getTotalPrice)
                .sum();
    }
}
