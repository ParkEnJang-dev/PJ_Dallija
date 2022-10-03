package com.spring.dallija.domain.order;

import com.spring.dallija.domain.Delivery;
import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

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

    public Orders(User user, Delivery delivery, OrdersItems... ordersItems) {
        this.user = user;
        this.delivery = delivery;
    }
}
