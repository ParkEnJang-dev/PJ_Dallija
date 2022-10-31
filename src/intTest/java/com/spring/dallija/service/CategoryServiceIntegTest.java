package com.spring.dallija.service;

import com.spring.dallija.domain.category.Category;
import com.spring.dallija.domain.category.CategoryType;
import com.spring.dallija.exception.category.DuplicateCategoryException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryServiceIntegTest {

    @Autowired
    CategoryService categoryService;

    @Test
    public void 카테고리_타입_중복() throws Exception {
        Assertions.assertThrows(DuplicateCategoryException.class,
                () -> categoryService
                        .validateDuplicateCategory(CategoryType.MACHINE)
        );
    }

    @Test
    public void 카테고리_저장() throws Exception {
        //given
        Category category = new Category(CategoryType.FOOD);

        //when
        Category result = categoryService.saveCategory(category);

        //then
        assertThat(category.getId()).isEqualTo(result.getId());
     }

}