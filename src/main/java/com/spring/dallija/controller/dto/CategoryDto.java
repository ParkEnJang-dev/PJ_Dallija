package com.spring.dallija.controller.dto;

import com.spring.dallija.domain.category.Category;
import com.spring.dallija.domain.category.CategoryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

public class CategoryDto {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveCategoryRequest{

        @NotEmpty(message = "카테고리 이름이 없습니다.")
        private String name;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CategoryResponse{

        private Long id;
        private String name;
        private CategoryStatus status;

        public CategoryResponse(Category category) {
            this.id = category.getId();
            this.name = category.getName();
            this.status = category.getStatus();
        }
    }
}
