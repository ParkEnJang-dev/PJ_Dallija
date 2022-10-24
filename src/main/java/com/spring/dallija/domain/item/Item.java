package com.spring.dallija.domain.item;


import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.category.CategoryItem;
import com.spring.dallija.exception.order.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;
    private Integer stockQuantity;
    //상품원산지
    private String originCity;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItem = new ArrayList<>();

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
        this.stockQuantity += quantity;
    }

    public void removeStockQuantity(int quantity) {
        int tempStock = this.stockQuantity - quantity;
        if (tempStock < 0) {
            this.status = ItemStatus.SOLDOUT;
            throw new NotEnoughStockException("재고가 없습니다.");
        }
        this.stockQuantity = tempStock;
    }
}
