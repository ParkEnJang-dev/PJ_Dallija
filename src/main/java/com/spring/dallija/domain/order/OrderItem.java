package com.spring.dallija.domain.order;


import com.spring.dallija.domain.item.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_seq")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int quantity; //주문 수량

    public void addOrder(Order order){
        this.order = order;
    }

    public OrderItem(Item item, int orderPrice, int quantity) {
        this.item = item;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem ordersItem =
                new OrderItem(
                        item,
                        orderPrice,
                        count
                );
        item.removeStockQuantity(count);
        return ordersItem;
    }


    //재고 수량 추가
    public void cancel(){
        getItem().addStockQuantity(quantity);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getQuantity();
    }

}
