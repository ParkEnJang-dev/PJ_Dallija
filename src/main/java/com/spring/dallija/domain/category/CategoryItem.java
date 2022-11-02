package com.spring.dallija.domain.category;

import com.spring.dallija.domain.item.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CategoryItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public CategoryItem(Category category) {
        this.category = category;
    }

    public void addItem(Item item){
        this.item = item;
    }

    public static CategoryItem createCategoryItem(Category category){
        CategoryItem categoryItem = new CategoryItem(category);
        return categoryItem;
    }
}
