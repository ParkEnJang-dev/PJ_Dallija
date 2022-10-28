package com.spring.dallija.controller;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.spring.dallija.controller.dto.OrderDto.*;
import static com.spring.dallija.controller.dto.OrderDto.FindAllOrdersResponse;
import static com.spring.dallija.controller.dto.OrderDto.SaveOrderRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrdersApiController {

    private final OrdersService ordersService;

    @LoginCheck(userRole = UserRole.USER)
    @PostMapping("/order")
    public void saveOrders(@RequestBody @Valid SaveOrderRequest saveOrderRequest) {
        ordersService.saveOrder(saveOrderRequest);

    }

    //모든 주문 조회.
    //한방 쿼리
    @LoginCheck(userRole = UserRole.ADMIN)
    @GetMapping("/order")
    public List<FindAllOrdersResponse> allOrders() {
        return ordersService.findAll();
    }

    @LoginCheck
    @GetMapping("/order/{id}")
    public Page<OrderUserResponse> finduser(@PathVariable Long id, Pageable pageable) {
        return ordersService.findOrderUsers(new OrderCond(id), pageable);
    }

}
