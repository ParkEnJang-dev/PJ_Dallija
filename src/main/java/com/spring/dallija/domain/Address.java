package com.spring.dallija.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String street;
    private String zipcode;

    public static Address createAddress(String street, String zipcode) {
        return new Address(street, zipcode);
    }
}
