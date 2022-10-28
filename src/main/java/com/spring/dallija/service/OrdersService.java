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
import com.spring.dallija.repository.item.ItemRepository;
import com.spring.dallija.repository.order.OrderRepository;
import com.spring.dallija.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.spring.dallija.controller.dto.OrderDto.*;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository ordersRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long saveOrder(SaveOrderRequest saveOrderRequest) {

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

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        //주문 생성
        Order order = Order.createOrder(user, delivery, orderItems);

        //주문 저장
        ordersRepository.save(order);

        return order.getId();
    }

    public List<FindAllOrdersResponse> findAll(){
        List<Order> orders = ordersRepository.findAll();

        List<FindAllOrdersResponse> result = orders.stream()
                .map(FindAllOrdersResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    public void cancelOrder(Long orderId){
        Order order = ordersRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.cancel();
    }

    public List<Order> findOrderUsers(OrderCond orderCond){
        return ordersRepository.findOrderUsers(orderCond);
    }

}
