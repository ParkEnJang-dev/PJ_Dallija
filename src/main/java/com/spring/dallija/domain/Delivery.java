package com.spring.dallija.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    //꼭 String 중간에 값이 들어올경우 , 기존 값이 밀리기때문에
    //장애 난다.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP
}
