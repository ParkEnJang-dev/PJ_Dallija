package com.spring.dallija.controller.dto;

import com.spring.dallija.domain.cart.Cart;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CartDto {

    @Getter
    @ToString
    @NoArgsConstructor
    public static class CartResponse{
        private Long id;
        private Long itemId;
        private String itemName;
        private Integer itemQuantity;
        private Integer itemPrice;

        public CartResponse(Cart cart) {
            this.id = cart.getId();
            this.itemId = cart.getId();
            this.itemName = cart.getItem().getName();
            this.itemQuantity= cart.getQuantity();
            this.itemPrice = cart.getItem().getPrice();
        }

        public static CartResponse CreateCartResponse(Cart cart){
            CartResponse cartResponse = new CartResponse(cart);
            return cartResponse;

        }
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

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateCartRequest{

        @NotNull(message = "장바구니 아이디 입력해주세요")
        private Long id;

        @NotNull(message = "수량을 입력해 주세요")
        @Positive
        private Integer quantity;

        public UpdateCartRequest(Long id, Integer quantity) {
            this.id = id;
            this.quantity = quantity;
        }
    }
}