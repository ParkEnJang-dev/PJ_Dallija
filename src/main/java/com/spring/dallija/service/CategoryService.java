package com.spring.dallija.service;


import com.spring.dallija.controller.dto.CategoryDto;
import com.spring.dallija.controller.dto.ItemDto;
import com.spring.dallija.domain.category.Category;
import com.spring.dallija.exception.category.DuplicateCategoryException;
import com.spring.dallija.exception.category.NotFoundCategoryException;
import com.spring.dallija.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.dallija.controller.dto.CategoryDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Category saveCategory(Category category) {
        validateDuplicateCategory(category.getName());
        return categoryRepository.save(category);
    }

    public void validateDuplicateCategory(String name) {
        categoryRepository.findByName(name)
                .ifPresent(c -> {
                    throw new DuplicateCategoryException();
                });
    }

    public Category findCategory(String name) {
        return categoryRepository.findByName(name)
                .orElseThrow(NotFoundCategoryException::new);
    }

    @Transactional
    public void inActiveCategory(Long id){
        Category findCategory = categoryRepository.findById(id)
                .orElseThrow(NotFoundCategoryException::new);
        findCategory.inActive();
    }

    public Page<CategoryResponse> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable).map(CategoryResponse::new);
    }
}