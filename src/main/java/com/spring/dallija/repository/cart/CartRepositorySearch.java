package com.spring.dallija.repository.cart;

import com.spring.dallija.controller.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static com.spring.dallija.controller.dto.CartDto.*;

public interface CartRepositorySearch {

    Page<CartResponse> findUserCarts(Long id, Pageable pageable);
}