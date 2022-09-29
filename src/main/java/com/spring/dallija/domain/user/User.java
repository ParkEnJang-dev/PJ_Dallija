package com.spring.dallija.domain.user;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.Health;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;


@Entity
@Getter
@AllArgsConstructor
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

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void changeName(String name){
        this.name = name;
    }
}
