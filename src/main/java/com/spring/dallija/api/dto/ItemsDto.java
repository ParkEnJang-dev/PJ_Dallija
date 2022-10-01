package com.spring.dallija.api.dto;

import com.spring.dallija.domain.item.Items;
import com.spring.dallija.domain.item.Meat;
import com.spring.dallija.domain.item.Vegetable;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class ItemsDto {


    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveItemsRequest {

        @NotEmpty
        String name;

        @NotNull
        Integer price;

        @NotNull
        Integer stockQuantity;

        @NotEmpty
        private String dtype;

        private String originCity;
        private String meatType;
        private String vegetableType;

        private Items toEntity() {
            Date date = new Date();
            switch (dtype) {
                case "meat":
                    if (meatType.isEmpty()){
                        throw new NullPointerException("meatType 필드가 비어있다.");
                    }
                    return new Meat(name, price, stockQuantity, originCity, LocalDate.now(), meatType);
                case "vegetable":
                    if (meatType.isEmpty()){
                        throw new NullPointerException("vegetableType 필드가 비어있다.");
                    }
                    return new Vegetable(name, price, stockQuantity, originCity, LocalDate.now(), vegetableType);
            }
            return null;
        }

    }
}
