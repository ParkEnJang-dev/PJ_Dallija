package com.spring.dallija.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

//@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long _id;
    private String name;
    private String email;
    private String password;
    private int sex; // 0 : 남자 1 : 여자
    private int height;
    private Timestamp birth;
    private Timestamp created_at;
    private Timestamp updated_at;
}
