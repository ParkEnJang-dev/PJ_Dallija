package com.spring.dallija.service;


import com.spring.dallija.domain.category.Category;
import com.spring.dallija.exception.category.DuplicateCategoryException;
import com.spring.dallija.repository.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}