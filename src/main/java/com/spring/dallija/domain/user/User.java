package com.spring.dallija.domain.user;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.order.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    private String password;
    private Date birth;

    @Embedded
    private Address address;

    @Embedded
    private Health health;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        status = UserStatus.NORMAL;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        status = UserStatus.NORMAL;
    }

    public User(String name, String email, String password, Address address, Health health) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.health = health;
        status = UserStatus.NORMAL;
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        status = UserStatus.NORMAL;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
