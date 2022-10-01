package com.spring.dallija.domain.item;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@DiscriminatorValue("meat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meat extends Items {

    @Enumerated(value = EnumType.STRING)
    private MeatType meatType;


    public Meat(String name, Integer price, Integer stockQuantity, String originCity, LocalDate created, String meatType) {
        super(name, price, stockQuantity, originCity, created);
        switch (meatType) {
            case "PORK" :
                this.meatType = MeatType.PORK;
                break;
            case "BEEF" :
                this.meatType = MeatType.BEEF;
                break;
            case "CHICKEN" :
                this.meatType = MeatType.CHICKEN;
                break;
        }
    }
}
