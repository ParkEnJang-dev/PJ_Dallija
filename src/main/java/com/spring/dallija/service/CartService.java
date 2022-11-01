package com.spring.dallija.service;

import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.exception.item.ItemNotFoundException;
import com.spring.dallija.exception.user.UserNotFoundException;
import com.spring.dallija.repository.cart.CartRepository;
import com.spring.dallija.repository.item.ItemRepository;
import com.spring.dallija.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.spring.dallija.controller.dto.CartDto.SaveCartRequest;

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
    public void deleteCart(Long id){
        cartRepository.deleteById(id);
    }
}
