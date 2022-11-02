package com.spring.dallija.domain.item;


import com.spring.dallija.controller.dto.ItemDto;
import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.domain.category.CategoryItem;
import com.spring.dallija.exception.order.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Integer stockQuantity;
    //상품원산지
    private String originCity;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<CategoryItem> categoryItem = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();

    public Item(String name, int price, int stockQuantity, String originCity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.originCity = originCity;
        this.status = ItemStatus.ACTIVED;
    }

    public void changeItem(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStockQuantity(int quantity) {
        if (this.status == ItemStatus.SOLDOUT){
            this.status = ItemStatus.ACTIVED;
        }
        this.stockQuantity += quantity;
    }

    public void removeStockQuantity(int quantity) {
        int tempStock = this.stockQuantity - quantity;
        if (tempStock < 0) {
            throw new NotEnoughStockException("재고가 없습니다.");
        }

        if (tempStock == 0) {
            this.status = ItemStatus.SOLDOUT;
        }

        this.stockQuantity = tempStock;
    }

}
