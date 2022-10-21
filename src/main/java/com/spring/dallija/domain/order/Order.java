package com.spring.dallija.domain.order;

import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.delivery.Delivery;
import com.spring.dallija.domain.delivery.DeliveryStatus;
import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> ordersItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderItem(OrderItem ordersItem) {
        this.ordersItems.add(ordersItem);
        ordersItem.addOrder(this);
    }

    public void addDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.addOrders(this);
    }

    public Order(User user, LocalDateTime orderTime, OrderStatus status, Delivery delivery) {
        addUser(user);
        this.orderTime = orderTime;
        this.status = status;
        addDelivery(delivery);
    }

    public static Order createOrder(User user, Delivery delivery, OrderItem... ordersItems) {
        Order order = new Order(
                user,
                LocalDateTime.now(),
                OrderStatus.ORDER,
                delivery
        );

        for (OrderItem ordersItem : ordersItems) {
            order.addOrderItem(ordersItem);
        }

        return order;
    }

    /**
     * 주문 취소
     */
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.SHIPPING) {
            throw new IllegalStateException("배송중인 상품은 취소가 불가능합니다.");
        }

        this.status = OrderStatus.CANCEL;
        this.ordersItems.forEach(OrderItem::cancel);
    }

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return this.ordersItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
