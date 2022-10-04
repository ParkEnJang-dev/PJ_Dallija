package com.spring.dallija.service;

import com.spring.dallija.api.dto.OrderDto;
import com.spring.dallija.domain.Address;
import com.spring.dallija.domain.Delivery;
import com.spring.dallija.domain.item.Items;
import com.spring.dallija.domain.order.Orders;
import com.spring.dallija.domain.order.OrdersItems;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.repository.ItemRepositoryImpl;
import com.spring.dallija.repository.OrdersRepositoryImpl;
import com.spring.dallija.repository.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        Items item = itemRepository.findOne(saveOrderRequest.getItemId());

        Delivery delivery = new Delivery(new Address(
                saveOrderRequest.getStreet(),
                saveOrderRequest.getZipcode()));

        OrdersItems ordersItem = OrdersItems.createOrdersItem(
                item,
                item.getPrice(),
                saveOrderRequest.getCount()
        );

        //주문 생성
        Orders order = Orders.createOrder(user, delivery, ordersItem);

        //주문 저장
        ordersRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId){
        Orders orders = ordersRepository.findOne(orderId);

        orders.cancel();
    }

}
