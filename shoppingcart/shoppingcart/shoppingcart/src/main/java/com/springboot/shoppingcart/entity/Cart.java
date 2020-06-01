package com.springboot.shoppingcart.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Table(name="Cart")
@Data
@Entity
public class Cart {

	@Id
	int cartId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cart")
	private List<Product> products;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", products=" + products + ", user=" + user + "]";
	}
	
	
}
