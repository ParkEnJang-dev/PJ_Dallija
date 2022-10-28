package com.spring.dallija.service;

import com.spring.dallija.controller.dto.OrderDto;
import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.delivery.Delivery;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.order.OrderItem;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.exception.item.ItemNotFoundException;
import com.spring.dallija.exception.order.OrderNotFoundException;
import com.spring.dallija.exception.user.UserNotFoundException;
import com.spring.dallija.repository.ItemRepository;
import com.spring.dallija.repository.OrderRepository;
import com.spring.dallija.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository ordersRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveOrder(OrderDto.SaveOrderRequest saveOrderRequest) {

        User user = userRepository.findById(saveOrderRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);
        Item item = itemRepository.findById(saveOrderRequest.getItemId())
                .orElseThrow(ItemNotFoundException::new);

        Delivery delivery = new Delivery(new Address(
                saveOrderRequest.getStreet(),
                saveOrderRequest.getZipcode()));

        OrderItem orderItem = OrderItem.createOrderItem(
                item,
                item.getPrice(),
                saveOrderRequest.getCount()
        );

        //주문 생성
        Order order = Order.createOrder(user, delivery, orderItem);

        //주문 저장
        ordersRepository.save(order);

        return order.getId();
    }

    public List<OrderDto.FindAllOrdersResponse> findAll(){
        List<Order> orders = ordersRepository.findAll();

        List<OrderDto.FindAllOrdersResponse> result = orders.stream()
                .map(OrderDto.FindAllOrdersResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    public void cancelOrder(Long orderId){
        Order order = ordersRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.cancel();
    }

}
