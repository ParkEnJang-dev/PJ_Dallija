package com.spring.dallija.domain.item;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Entity
@Getter
@DiscriminatorValue("vegetable")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vegetable extends Items {
    @Enumerated(value = EnumType.STRING)
    private VegetableType vegetableType;
    private String originCity;
    private Date created;
}
