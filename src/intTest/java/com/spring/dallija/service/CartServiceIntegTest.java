package com.spring.dallija.service;

import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.domain.user.User;
import com.spring.dallija.repository.cart.CartRepository;
import com.spring.dallija.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.spring.dallija.controller.dto.CartDto.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CartServiceIntegTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 장바구니_저장() throws Exception {
        //given
        SaveCartRequest saveCartRequest = new SaveCartRequest(2L, 2);

        //when
        Cart result = cartService.addCart("ABC@naver.com", saveCartRequest);

        //then
        assertThat(result.getItem().getId()).isEqualTo(saveCartRequest.getItemId());
    }

    @Test
    public void 장바구니_아이템_하나삭제() throws Exception {
        //given
        SaveCartRequest saveCartRequest = new SaveCartRequest(2L, 2);
        Cart result = cartService.addCart("ABC@naver.com", saveCartRequest);

        //when
        cartService.deleteCart(result.getId());

        //then
        assertThat(cartRepository.findById(result.getId())).isEmpty();
    }

    @Test
    public void 장바구니_아이템_수량변경() throws Exception {
        //given
        SaveCartRequest saveCartRequest = new SaveCartRequest(2L, 2);
        Cart saveCart = cartService.addCart("ABC@naver.com", saveCartRequest);
        UpdateCartRequest updateCartRequest = new UpdateCartRequest(saveCart.getId(), 1);

        //when
        cartService.updateCart(updateCartRequest);

        //then
        Cart result = cartRepository.findById(saveCart.getId()).get();
        assertThat(result.getQuantity()).isEqualTo(1);
    }

    @Test
    public void 유저장바구니_조회() throws Exception {
        //given
        SaveCartRequest saveCartRequest1 = new SaveCartRequest(2L, 2);
        SaveCartRequest saveCartRequest2 = new SaveCartRequest(11L, 2);
        cartService.addCart("ABC@naver.com", saveCartRequest1);
        cartService.addCart("ABC@naver.com", saveCartRequest2);
        User findUser = userRepository.findByEmail("ABC@naver.com").get();

        Pageable pageable = PageRequest.of(0, 10);
        //when
        Page<CartResponse> userCarts = cartService.findUserCarts(findUser.getId(), pageable);

        //then
        assertThat(userCarts.getContent().size()).isEqualTo(2);

    }
}