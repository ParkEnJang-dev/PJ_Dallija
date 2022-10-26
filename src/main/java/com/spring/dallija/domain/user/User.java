package com.spring.dallija.domain.user;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.BaseTimeEntity;
import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.domain.order.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
    private Date birth;

    @Embedded
    private Address address;

    @Embedded
    private Health health;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Cart> carts = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        status = UserStatus.NORMAL;
        userRole = UserRole.USER;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        status = UserStatus.NORMAL;
        userRole = UserRole.USER;
    }

    public User(String name, String email, String password, Address address, Health health) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.health = health;
        status = UserStatus.NORMAL;
        userRole = UserRole.USER;
    }

    public User(String name, String email, String password, UserRole userRole, Address address, Health health) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.health = health;
        this.userRole = userRole;
        status = UserStatus.NORMAL;
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        status = UserStatus.NORMAL;
        userRole = UserRole.USER;
    }

    public void changeName(String name) {
        this.name = name;
    }

}
