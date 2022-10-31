package com.spring.dallija.controller.dto;

import com.spring.dallija.validation.annotation.OnlyCategoryType;
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

        @NotEmpty(message = "카테고리 타입이 없습니다.")
        @OnlyCategoryType
        private String categoryType;
    }
}
