package com.spring.dallija.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
public class Health {

    private Integer height;
    private Integer weight;
    private String gender;

    protected Health() {

    }
}
