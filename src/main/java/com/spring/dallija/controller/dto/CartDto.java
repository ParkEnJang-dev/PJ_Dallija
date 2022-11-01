package com.spring.dallija.controller.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

        @NotNull(message = "아이템 아이디 입력해주세요")
        private Long itemId;

        @NotNull(message = "수량을 입력해 주세요")
        @Positive
        private Integer quantity;

        public SaveCartRequest(Long itemId, Integer quantity) {
            this.itemId = itemId;
            this.quantity = quantity;
        }
    }
}