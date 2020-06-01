package com.springboot.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.shoppingcart.entity.Product;

public interface ShoppingCartRepository extends JpaRepository<Product, Integer>{

	public List<Product> findByCartId(int cartId);
//	public List<Product> findByCartId(int cartId);
	
}
