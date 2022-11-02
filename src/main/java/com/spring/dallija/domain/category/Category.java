package com.spring.dallija.domain.category;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

    public Category(String name) {
        this.name = name;
        this.status = CategoryStatus.ACTIVE;
    }

    public void inActive(){
        this.status = CategoryStatus.INACTIVE;
    }
}
