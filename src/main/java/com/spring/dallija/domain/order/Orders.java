package com.spring.dallija.domain.order;

import com.spring.dallija.domain.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue
    @Column(name = "order_seq")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_seq")
    private User user;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
