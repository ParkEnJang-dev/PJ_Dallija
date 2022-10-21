package com.spring.dallija.api.dto;

import com.spring.dallija.domain.item.Item;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ItemsDto {


    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveItemsRequest {

        @NotEmpty
        private String name;

        @NotNull
        private Integer price;

        @NotNull
        private Integer stockQuantity;

        @NotEmpty
        private String dtype;

        private String originCity;
        private String meatType;
        private String vegetableType;

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
