package com.spring.dallija.controller.dto;

import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.item.ItemStatus;
import com.spring.dallija.domain.user.User;
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

        private String categoryName;

        public Item toEntity() {
            return new Item(name, price, stockQuantity, originCity);
        }

        public SaveItemsRequest(Item item) {
            this.name = item.getName();
            this.price = item.getPrice();
            this.stockQuantity = item.getStockQuantity();
            this.originCity = item.getOriginCity();
        }

        public void addCategoryName(String name){
            this.categoryName = name;
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

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ItemResponse {

        private Long id;
        private String name;
        private Integer price;
        private Integer stockQuantity;
        private String originCity;
        private ItemStatus status;

        public ItemResponse(Item item){
            this.id = item.getId();
            this.name = item.getName();
            this.price = item.getPrice();
            this.stockQuantity = item.getStockQuantity();
            this.originCity = item.getOriginCity();
            this.status = item.getStatus();
        }

    }
}
