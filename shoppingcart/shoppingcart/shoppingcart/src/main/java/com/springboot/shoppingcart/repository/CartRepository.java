package com.springboot.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.shoppingcart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
