package com.spring.dallija.service;

import com.spring.dallija.domain.cart.Cart;
import com.spring.dallija.repository.cart.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.spring.dallija.controller.dto.CartDto.SaveCartRequest;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CartServiceIntegTest {

    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

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
}