package com.spring.dallija.domain.payment;


import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.order.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    private String payWay;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @OneToOne(mappedBy = "payment", fetch = LAZY)
    private Order order;
}