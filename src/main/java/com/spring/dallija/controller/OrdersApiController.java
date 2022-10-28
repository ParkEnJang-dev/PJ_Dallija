package com.spring.dallija.controller;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.user.UserRole;
import com.spring.dallija.repository.order.OrderRepository;
import com.spring.dallija.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.dallija.controller.dto.OrderDto.*;

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

    @GetMapping("/order/test")
    public void finduser(@RequestBody OrderCond orderCond){
        ordersService.findOrderUsers(orderCond);
    }

    /*@GetMapping("/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = ordersRepository.findAllByUser(new OrderSearch());
        return all;
    }

    //성능 안나옴. N + 1 LAZY 때문.
    @GetMapping("/v2/simple-orders")
    public List<FindAllOrdersResponse> ordersV2() {
        List<Order> orders = ordersRepository.findAllByUser(new OrderSearch());

        List<FindAllOrdersResponse> result = orders.stream()
                .map(o -> new FindAllOrdersResponse(o))
                .collect(Collectors.toList());

        return result;
    }*/

    //간결하지만 유연하지 못하다.
    /*@GetMapping("/v4/simple-orders")
    public List<OrdersDto> allOrders2(){
        return ordersRepository.findAllByUser();
    }*/

}
