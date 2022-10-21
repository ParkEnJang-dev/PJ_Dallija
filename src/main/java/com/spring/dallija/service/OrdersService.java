package com.spring.dallija.service;

import com.spring.dallija.api.dto.OrderDto;
import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.Delivery;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.order.Order;
import com.spring.dallija.domain.order.OrderItem;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.repository.ItemRepositoryImpl;
import com.spring.dallija.repository.OrdersRepositoryImpl;
import com.spring.dallija.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepositoryImpl ordersRepository;
    private final UserRepositoryImpl userRepository;
    private final ItemRepositoryImpl itemRepository;

    @Transactional
    public Long saveOrder(OrderDto.SaveOrderRequest saveOrderRequest) {

        User user = userRepository.findById(saveOrderRequest.getUserId()).get();
        Item item = itemRepository.findOne(saveOrderRequest.getItemId());

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
        List<Order> orders = ordersRepository.findAllWithUserDeliver();

        List<OrderDto.FindAllOrdersResponse> result = orders.stream()
                .map(o -> new OrderDto.FindAllOrdersResponse(o))
                .collect(Collectors.toList());

        return result;
    }

    public void cancelOrder(Long orderId){
        Order order = ordersRepository.findOne(orderId);

        order.cancel();
    }

}
