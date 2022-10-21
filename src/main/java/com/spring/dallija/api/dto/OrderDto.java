package com.spring.dallija.api.dto;

import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.order.OrderStatus;
import com.spring.dallija.domain.order.Order;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
            this.orderDate = order.getOrderTime();
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
