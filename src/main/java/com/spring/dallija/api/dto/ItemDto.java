package com.spring.dallija.api.dto;

import com.spring.dallija.domain.item.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItemDto {


    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveItemsRequest {

        @NotBlank(message = "제품명을 입력해주세요.")
        private String name;

        @Positive(message = "올바른 값을 입력해주세요")
        @NotNull(message = "제품 가격을 입력해 주세요.")
        private Integer price;

        @Positive(message = "올바른 값을 입력해주세요")
        @NotNull(message = "제품 수량을 입력해 주세요.")
        private Integer stockQuantity;
        private String originCity;

        public Item toEntity() {
            return new Item(name, price, stockQuantity, originCity);
        }

    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateItemsRequest {

        @NotEmpty
        private Long id;

        @NotEmpty
        private String name;

        @NotNull
        private Integer price;

        @NotNull
        private Integer stockQuantity;

        public UpdateItemsRequest(Long id, String name, Integer price, Integer stockQuantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }
    }
}
