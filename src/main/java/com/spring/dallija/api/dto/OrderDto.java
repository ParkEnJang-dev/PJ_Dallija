package com.spring.dallija.api.dto;

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


    }
}
