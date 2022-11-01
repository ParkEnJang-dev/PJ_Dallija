package com.spring.dallija.repository.category;

import com.spring.dallija.domain.category.Category;
import com.spring.dallija.domain.category.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}