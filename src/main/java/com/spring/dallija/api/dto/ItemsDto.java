package com.spring.dallija.api.dto;

import com.spring.dallija.domain.item.Items;
import com.spring.dallija.domain.item.Meat;
import com.spring.dallija.domain.item.Vegetable;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

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

        public Items toEntity() {
            Date date = new Date();
            switch (dtype) {
                case "meat":
                    if (meatType.isEmpty()){
                        throw new NullPointerException("meatType 필드가 비어있다.");
                    }
                    return new Meat(name, price, stockQuantity, originCity, LocalDateTime.now(), meatType);
                case "vegetable":
                    if (meatType.isEmpty()){
                        throw new NullPointerException("vegetableType 필드가 비어있다.");
                    }
                    return new Vegetable(name, price, stockQuantity, originCity, LocalDateTime.now(), vegetableType);
            }
            return null;
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
