package com.spring.dallija.service;

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
import com.spring.dallija.repository.order.OrderItemRepository;
import com.spring.dallija.repository.order.OrderRepository;
import com.spring.dallija.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Order saveOrder(SaveOrderRequest saveOrderRequest) {

        User user = userRepository.findById(saveOrderRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);
        Item item = itemRepository.findById(saveOrderRequest.getItemId())
                .orElseThrow(ItemNotFoundException::new);

        Delivery delivery = Delivery.createDelivery(Address.createAddress(
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
        return ordersRepository.save(order);
    }

    @Transactional
    public void saveCartOrder(SaveOrderMultiRequest saveOrderMultiRequest) {
        User user = userRepository.findById(saveOrderMultiRequest.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Delivery delivery = Delivery.createDelivery(Address.createAddress(
                saveOrderMultiRequest.getStreet(),
                saveOrderMultiRequest.getZipcode()));

        //아이템 가져오기
        List<OrderItem> orderItems = createOrderItems(saveOrderMultiRequest.getSaveOrderItems());

        //주문 생성
        Order order = Order.createOrder(user, delivery, orderItems);

        //주문 저장
        ordersRepository.save(order);
    }

    public List<FindAllOrdersResponse> findAll() {
        List<Order> orders = ordersRepository.findAll();

        List<FindAllOrdersResponse> result = orders.stream()
                .map(FindAllOrdersResponse::new)
                .collect(Collectors.toList());

        return result;
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = ordersRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        order.cancel();
    }

    public Page<OrderUserResponse> findOrderUser(OrderCond orderCond, Pageable pageable) {
        return ordersRepository.findOrderUser(orderCond,pageable);
    }

    //주문 상세 조회
    public Page<OrderItemResponse> findOrderDetail(OrderCond orderCond, Pageable pageable){
        return orderItemRepository.findByOrderItems(orderCond,pageable);
    }

    //단건 주문 조회
    public Order findById(Long id){
        return ordersRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    private List<OrderItem> createOrderItems (List<SaveOrderItem> saveOrderItems){
        List<OrderItem> orderItems = new ArrayList<>();
        for (SaveOrderItem saveOrderItem : saveOrderItems) {

            Item item = itemRepository.findById(saveOrderItem.getId())
                    .orElseThrow(ItemNotFoundException::new);

            OrderItem orderItem = OrderItem.createOrderItem(
                    item,
                    item.getPrice(),
                    saveOrderItem.getQuantity()
            );

            orderItems.add(orderItem);
        }

        return orderItems;
    }
}