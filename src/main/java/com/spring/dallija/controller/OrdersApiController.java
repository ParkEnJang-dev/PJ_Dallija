package com.spring.dallija.controller;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.spring.dallija.controller.dto.OrderDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrdersApiController {

    private final OrdersService ordersService;

    @LoginCheck(userRole = UserRole.USER)
    @PostMapping
    public void saveOrder(@RequestBody @Valid SaveOrderRequest saveOrderRequest) {
        ordersService.saveOrder(saveOrderRequest);
    }

    //모든 주문 조회.
    @LoginCheck(userRole = UserRole.ADMIN)
    @GetMapping
    public List<FindAllOrdersResponse> allOrders() {
        return ordersService.findAll();
    }

    //회원 주문내역 조회
    //@LoginCheck
    @GetMapping("/{id}")
    public Page<OrderUserResponse> findOrderUser(@PathVariable Long id, Pageable pageable) {
        return ordersService.findOrderUser(new OrderCond(id), pageable);
    }

    @LoginCheck
    @DeleteMapping("/{id}")
    public void cancelOrder(@PathVariable Long id) {
        ordersService.cancelOrder(id);
    }

    //주문 상세보기
    @LoginCheck
    @GetMapping("/detail/{id}")
    public Page<OrderItemResponse> findOrderDetails(@PathVariable Long id, Pageable pageable) {
        return ordersService.findOrderDetail(new OrderCond(id), pageable);
    }

    @PostMapping("/multi")
    public void saveMultiOrder(@RequestBody @Valid SaveOrderMultiRequest saveOrderMultiRequest) {
        ordersService.saveCartOrder(saveOrderMultiRequest);
    }
}
