package com.spring.dallija.domain.item;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
@Getter
@DiscriminatorValue("vegetable")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vegetable extends Items {

    @Enumerated(value = EnumType.STRING)
    private VegetableType vegetableType;

    public Vegetable(String name, Integer price, Integer stockQuantity, String originCity, LocalDate created, String vegetableType) {
        super(name, price, stockQuantity, originCity, created);
        switch (vegetableType) {
            case "CABBAGE":
                this.vegetableType = VegetableType.CABBAGE;
                break;
            case "CARROT":
                this.vegetableType = VegetableType.CARROT;
                break;
        }
    }

}
