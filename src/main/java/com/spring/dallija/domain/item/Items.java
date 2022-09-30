package com.spring.dallija.domain.item;


import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@DiscriminatorColumn(name = "dtype")
public class Items {

    @Id
    @GeneratedValue
    @Column(name = "item_seq")
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    private int price;
    private int stock_quantity;
}
