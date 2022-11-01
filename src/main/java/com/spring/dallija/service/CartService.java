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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.dallija.controller.dto.CartDto.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public Cart addCart(String email, SaveCartRequest saveCartRequest){
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        Item item = itemRepository.findById(saveCartRequest.getItemId())
                .orElseThrow(ItemNotFoundException::new);

        Cart cart = Cart.createCart(user, item, saveCartRequest.getQuantity());

        return cartRepository.save(cart);
    }

    @Transactional
    public void updateCart(UpdateCartRequest updateCartRequest){
        Cart cart = cartRepository.findById(updateCartRequest.getId())
                .orElseThrow(CartNotFoundException::new);
        cart.changeQuantity(updateCartRequest.getQuantity());
    }

    @Transactional
    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }

    public Page<CartResponse> findUserCarts(Long id, Pageable pageable){
        return cartRepository.findUserCarts(id,pageable);
    }
}
