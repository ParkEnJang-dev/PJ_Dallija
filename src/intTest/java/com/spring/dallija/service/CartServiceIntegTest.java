package com.spring.dallija.service;

import com.spring.dallija.domain.cart.Cart;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.spring.dallija.controller.dto.CartDto.SaveCartRequest;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CartServiceIntegTest {

    @Autowired
    CartService cartService;


    @Test
    @Rollback(value = false)
    public void 장바구니_저장() throws Exception {
        //given
        SaveCartRequest saveCartRequest = new SaveCartRequest(2L, 2);

        //when
        Cart result = cartService.addCard("ABC@naver.com", saveCartRequest);

        //then
        assertThat(result.getItem().getId()).isEqualTo(saveCartRequest.getItemId());
     }


}
