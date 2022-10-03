package com.spring.dallija.domain.item;


import com.spring.dallija.domain.category.CategoryItems;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Items {

    @Id
    @GeneratedValue
    @Column(name = "item_seq")
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private Integer price;
    private Integer stockQuantity;
    private String originCity;
    private LocalDateTime created;

    @OneToMany(mappedBy = "items")
    private List<CategoryItems> categoryItems = new ArrayList<>();

    Items(String name, int price, int stockQuantity, String originCity, LocalDateTime created) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.originCity = originCity;
        this.created = created;
    }

    public void changeItem(String name, Integer price, Integer stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
