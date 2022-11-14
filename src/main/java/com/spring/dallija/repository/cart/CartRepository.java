package com.spring.dallija.repository.cart;

import com.spring.dallija.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositorySearch {
    @Query("select c from Cart c join c.user u where u.id = :itemId")
    List<Cart> findCartByItem(@Param("itemId") Long id);
}