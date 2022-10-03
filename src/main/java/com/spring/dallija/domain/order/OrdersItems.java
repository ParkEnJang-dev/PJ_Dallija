package com.spring.dallija.domain.order;


import com.spring.dallija.domain.item.Items;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrdersItems {

    @Id
    @GeneratedValue
    @Column(name = "orders_items_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_seq")
    private Items items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_seq")
    private Orders orders;

    private int orderPrice; //주문가격
    private int count; //주문 수량

    public void addOrder(Orders orders){
        this.orders = orders;
    }

    public OrdersItems(Items items, int orderPrice, int count) {
        this.items = items;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrdersItems createOrdersItem(Items item, int orderPrice, int count){
        OrdersItems ordersItem =
                new OrdersItems(
                        item,
                        orderPrice,
                        count
                );
        item.removeStockQuantity(count);
        return ordersItem;
    }


    //재고 수량 추가
    public void cancel(){
        getItems().addStockQuantity(count);
    }

    public int getTotalPrice() {
        return getOrderPrice() * getOrderPrice();
    }

}
