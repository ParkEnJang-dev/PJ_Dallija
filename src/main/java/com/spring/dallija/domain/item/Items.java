package com.spring.dallija.domain.item;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@DiscriminatorColumn(name = "dtype")
public class Items {
    @Id
    @GeneratedValue
    @Column(name = "item_seq")
    private Long id;

    private String name;
    private int price;
    private int stock_quantity;
}
