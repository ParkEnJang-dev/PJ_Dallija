package com.spring.dallija.repository.cart;

import com.spring.dallija.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositorySearch {
}