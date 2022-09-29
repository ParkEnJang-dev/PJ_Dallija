package com.spring.dallija.domain.user;

import com.spring.dallija.domain.Address;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
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
