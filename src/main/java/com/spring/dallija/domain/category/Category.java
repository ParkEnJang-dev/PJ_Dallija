package com.spring.dallija.domain.category;

import com.spring.dallija.domain.item.Items;
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
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;


}
