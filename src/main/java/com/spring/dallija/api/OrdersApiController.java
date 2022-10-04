package com.spring.dallija.api;

import com.spring.dallija.repository.OrdersRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrdersApiController {

    private final OrdersRepositoryImpl ordersRepository;


}
