package com.spring.dallija.controller;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.domain.category.Category;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring.dallija.controller.dto.CategoryDto.SaveCategoryRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryApiController {

    private final CategoryService categoryService;

    @LoginCheck(userRole = UserRole.ADMIN)
    @PostMapping
    public void saveCategory(@RequestBody @Valid SaveCategoryRequest request) {
        categoryService.saveCategory(new Category(request.getName()));
    }

    @LoginCheck(userRole = UserRole.ADMIN)
    @DeleteMapping("/{id}")
    public void inActiveCategory(@PathVariable Long id){
        categoryService.inActiveCategory(id);
    }
}
