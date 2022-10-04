package com.spring.dallija.api.dto;

import com.spring.dallija.domain.order.OrderStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderDto {

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveOrderRequest {
        @NotNull
        private Long userId;
        @NotNull
        private Long itemId;
        @NotNull
        private Integer count;
        @NotEmpty
        private String street;
        @NotEmpty
        private String zipcode;

        public SaveOrderRequest(Long userId, Long itemId, Integer count, String street, String zipcode) {
            this.userId = userId;
            this.itemId = itemId;
            this.count = count;
            this.street = street;
            this.zipcode = zipcode;
        }
    }

    @Getter
    @Setter
    public static class OrderSearch {
        private String userName;
        private OrderStatus orderStatus;
    }
}
