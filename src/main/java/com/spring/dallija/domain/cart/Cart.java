package com.spring.dallija.domain.cart;

import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_item")
public class Cart {

    @Id
    @GeneratedValue
    private long id;

    private Integer quantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Cart(User user, Item item, Integer quantity) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    public static Cart createCart(User user, Item item, Integer quantity) {
        return new Cart(user, item, quantity);
    }
}
