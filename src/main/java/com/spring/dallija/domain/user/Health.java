package com.spring.dallija.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Health {
    private Integer height;
    private Integer weight;
    @Enumerated(EnumType.STRING)
    private GenderStatus gender;
}
