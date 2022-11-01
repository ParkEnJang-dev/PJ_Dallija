package com.spring.dallija.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class CartDto {

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CartResponse{
        private Long id;
        private Long itemId;
        private String itemName;
        private Integer itemQuantity;
        private Integer itemPrice;
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveCartRequest{
        private Long itemId;
        private Integer quantity;
    }
}