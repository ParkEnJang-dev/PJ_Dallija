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
@DiscriminatorValue("meat")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meat extends Items {

    @Enumerated(value = EnumType.STRING)
    private MeatType meatType;
    private String originCity;
    private Date created;
}
