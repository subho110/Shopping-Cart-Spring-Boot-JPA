package com.springboot.shoppingcart.service;

import java.util.List;

import com.springboot.shoppingcart.entity.Book;
import com.springboot.shoppingcart.entity.Product;

public interface ShoppingCartService {

	// add / update
	public void addBook(Book book);

	public void addProduct(Product product);

	// retrieve / retrieve all
	List<Product> getAllProducts(int cartId);

	public int findProductQuantityInCart(int cartId);

	// delete one / delete all
	public void removeProduct(int cartId);

	public void removeAllProducts(int cartId);

	public void emptyCart();

	// others

	public float generateCartTotal(int cartId);

	void emptyTheCart(int cartId);

}
