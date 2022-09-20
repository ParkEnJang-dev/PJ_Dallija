package com.spring.dallija.domain;

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
    @GeneratedValue
    @Column(name = "user_seq")
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(unique = true)
    private String email;

    private Integer password;
    private Date birth;

    @Embedded
    private Address address;

    @Embedded
    private Health health;

}
