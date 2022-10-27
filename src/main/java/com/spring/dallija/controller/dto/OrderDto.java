package com.spring.dallija.controller.dto;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.order.OrderStatus;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class OrderDto {

    @Getter
    @ToString
    @EqualsAndHashCode
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SaveOrderRequest {
        @NotNull(message = "유저 아이디가 없습니다.")
        private Long userId;
        @NotNull(message = "아이템 아이디가 없습니다.")
        private Long itemId;
        @NotNull(message = "주문수량이 없습니다.")
        @Positive
        private Integer count;
        @NotEmpty(message = "주소지가 없습니다.")
        private String street;
        @NotEmpty(message = "우편번호가 없습니다.")
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

    @Data
    public static class FindAllOrdersResponse {

        private Long orderId;
        private String name;
        private LocalDateTime orderDate; //주문시간
        private OrderStatus orderStatus;
        private Address address;

        public FindAllOrdersResponse(Order order){
            this.orderId = order.getId();
            this.name = order.getUser().getName();
            this.orderStatus = order.getStatus();
            this.address = order.getDelivery().getAddress();
        }

        public FindAllOrdersResponse(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
            this.orderId = orderId;
            this.name = name;
            this.orderDate = orderDate;
            this.orderStatus = orderStatus;
            this.address = address;
        }
    }

}
