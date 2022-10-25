package com.spring.dallija.api;

import com.spring.dallija.api.dto.OrderDto;
import com.spring.dallija.api.dto.OrdersDto;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.repository.OrdersRepositoryImpl;
import com.spring.dallija.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrdersApiController {

    private final OrdersRepositoryImpl ordersRepository;
    private final OrdersService ordersService;

    @PostMapping("/orders")
    public Long saveOrders(){

        return 1L;
    }

    //모든 주문 조회.
    //한방 쿼리
    @GetMapping("/orders")
    public List<OrderDto.FindAllOrdersResponse> allOrders(){
        return ordersService.findAll();
    }

    @GetMapping("/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = ordersRepository.findAllByString(new OrderDto.OrderSearch());
        return all;
    }

    //성능 안나옴. N + 1 LAZY 때문.
    @GetMapping("/v2/simple-orders")
    public List<OrderDto.FindAllOrdersResponse> ordersV2(){
        List<Order> orders = ordersRepository.findAllByString(new OrderDto.OrderSearch());

        List<OrderDto.FindAllOrdersResponse> result = orders.stream()
                .map(o -> new OrderDto.FindAllOrdersResponse(o))
                .collect(Collectors.toList());

        return result;
    }

    //간결하지만 유연하지 못하다.
    @GetMapping("/v4/simple-orders")
    public List<OrdersDto> allOrders2(){
        return ordersRepository.findOrderDtos();
    }

}
