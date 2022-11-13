package com.spring.dallija.service;

import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.domain.item.Item;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.repository.cart.CartRepository;
import com.spring.dallija.repository.item.ItemRepository;
import com.spring.dallija.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static com.spring.dallija.controller.dto.CartDto.SaveCartRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CartServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void 카트_저장() throws Exception {
        //given
        User user = new User("min","min@naver.com");
        Item item = new Item("소고기 볶음",2000,10,"횡성");
        SaveCartRequest saveCartRequest = new SaveCartRequest(4L,2);
        Cart cart = Cart.createCart(user, item, saveCartRequest.getQuantity());

        when(userRepository.findByEmail("min@naver.com")).thenReturn(Optional.of(user));
        when(itemRepository.findById(4L)).thenReturn(Optional.of(item));
        when(cartRepository.save(any())).thenReturn(cart);

        //when
        Cart resultCart = cartService.addCartItem("min@naver.com", saveCartRequest);

        //then
        assertThat(resultCart.getItem().getName()).isEqualTo("소고기 볶음");
        assertThat(resultCart.getUser().getEmail()).isEqualTo("min@naver.com");
        assertThat(resultCart.getQuantity()).isEqualTo(2);
     }



}