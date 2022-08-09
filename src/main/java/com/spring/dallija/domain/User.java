package com.spring.dallija.domain;

import java.sql.Timestamp;

public class User {
    private long id;
    private String name;
    private String email;
    private String password;
    private int sex; // 0 : 남자 1 : 여자
    private int height;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getSex() {
        return sex;
    }

    public int getHeight() {
        return height;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    private Timestamp birth;
    private Timestamp created_at;
    private Timestamp updated_at;
}
