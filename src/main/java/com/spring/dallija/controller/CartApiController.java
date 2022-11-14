package com.spring.dallija.controller;

import com.spring.dallija.common.anotation.LoginCheck;
import com.spring.dallija.common.anotation.LoginUser;
import com.spring.dallija.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.spring.dallija.controller.dto.CartDto.CartResponse;
import static com.spring.dallija.controller.dto.CartDto.SaveCartRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartApiController {

    private final CartService cartService;

    @LoginCheck
    @PostMapping
    public void addCartItem(@LoginUser String email
            , @RequestBody @Valid SaveCartRequest saveCartRequest) {
        cartService.addCartItem(email, saveCartRequest);
    }

    @LoginCheck
    @PatchMapping("/{id}")
    public void updateCartItem(@PathVariable Long id,
                               @RequestParam Integer quantity) {
        cartService.updateCartItem(id, quantity);
    }

    @LoginCheck
    @DeleteMapping("/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(id);
    }

    @LoginCheck
    @GetMapping
    public Page<CartResponse> findUserCartItems(@LoginUser String email
            , Pageable pageable) {
        return cartService.findUserCartItems(email, pageable);
    }

    @LoginCheck
    @GetMapping("test")
    @ResponseBody
    public List<CartResponse> findUserTestCartItems(@LoginUser String email
    ) {
        return cartService.findUserTestCartItems(email);
    }
}