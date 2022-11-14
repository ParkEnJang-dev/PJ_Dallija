package com.spring.dallija.service;

import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.exception.cart.CartNotFoundException;
import com.spring.dallija.exception.item.ItemNotFoundException;
import com.spring.dallija.exception.user.UserNotFoundException;
import com.spring.dallija.repository.cart.CartRepository;
import com.spring.dallija.repository.item.ItemRepository;
import com.spring.dallija.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.spring.dallija.controller.dto.CartDto.CartResponse;
import static com.spring.dallija.controller.dto.CartDto.SaveCartRequest;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public Cart addCartItem(String email, SaveCartRequest saveCartRequest) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        Item item = itemRepository.findById(saveCartRequest.getItemId())
                .orElseThrow(ItemNotFoundException::new);

        Cart cart = Cart.createCart(user, item, saveCartRequest.getQuantity());

        return cartRepository.save(cart);
    }

    @Transactional
    public void updateCartItem(Long id, Integer quantity) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(CartNotFoundException::new);
        cart.changeQuantity(quantity);
    }

    @Transactional
    public void deleteCartItem(Long id) {
        cartRepository.deleteById(id);
    }

    public Page<CartResponse> findUserCartItems(String email, Pageable pageable) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return cartRepository.findUserCarts(user.getId(), pageable);
    }

    public List<CartResponse> findUserTestCartItems(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        List<Cart> carts = cartRepository.findCartByItem(user.getId());

        List<CartResponse> cartResponseStream = carts.stream().map(cart ->
                CartResponse.CreateCartResponse(cart)).collect(Collectors.toList());
        log.debug("cart size {}",carts.size());
        return cartResponseStream;
    }
}
