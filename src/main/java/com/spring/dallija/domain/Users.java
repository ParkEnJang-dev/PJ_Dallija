package com.spring.dallija.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.time.LocalDateTime;


@Entity
@Getter
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "user_seq")
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;
    private Integer password;
    private Date birth;

    @Embedded
    private Address address;

    @Embedded
    private Health health;

    protected Users() {

    }
}
