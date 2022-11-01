package com.spring.dallija.controller.dto;

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
}
