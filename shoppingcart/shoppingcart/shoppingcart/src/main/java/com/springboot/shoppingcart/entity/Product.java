package com.springboot.shoppingcart.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Product")
@NamedQueries({ 
	@NamedQuery(name= "Product.findByCartId" ,
			     query = "select u from Product u where u.cart.id = ?1")})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {

	@Id
	int productId;
	
	String productName;
	float price;
	int quantity;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "id")
	private Cart cart;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", quantity="
				+ quantity + ", cart=" + cart + "]";
	}
	
	
	
}
