package com.spring.dallija.domain.order;

import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.delivery.Delivery;
import com.spring.dallija.domain.delivery.DeliveryStatus;
import com.spring.dallija.domain.payment.Payment;
import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String title;
    private Integer price;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItem = new ArrayList<>();

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public void addUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    public void addOrderItem(OrderItem ordersItem) {
        this.orderItem.add(ordersItem);
        ordersItem.addOrder(this);
    }

    public void addDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.addOrders(this);
    }

    public Order(User user, Delivery delivery, OrderStatus status ) {
        this.status = status;
        addUser(user);
        addDelivery(delivery);
    }

    public static Order createOrder(User user, Delivery delivery, List<OrderItem> orderItems) {
        Order order = new Order(
                user,
                delivery,
                OrderStatus.ORDER
        );

        for (OrderItem ordersItem : orderItems) {
            order.addOrderItem(ordersItem);
        }

        order.title = order.createOrderTitle();
        order.price = order.getTotalPrice();

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
        this.orderItem.forEach(OrderItem::cancel);
    }

    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        return this.orderItem.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    /**
     * 주문 타이이틀 생성
     */
    private String createOrderTitle(){
        if (this.orderItem.size() > 1) {
            return this.orderItem.get(0).getItem().getName() + " 외 " + this.orderItem.size() + "개";
        }
        return this.orderItem.get(0).getItem().getName();
    }
}
