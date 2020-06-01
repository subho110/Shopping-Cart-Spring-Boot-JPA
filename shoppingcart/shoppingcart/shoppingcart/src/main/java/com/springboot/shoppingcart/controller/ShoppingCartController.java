package com.springboot.shoppingcart.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.shoppingcart.entity.Apparal;
import com.springboot.shoppingcart.entity.Book;
import com.springboot.shoppingcart.entity.Cart;
import com.springboot.shoppingcart.entity.Product;
import com.springboot.shoppingcart.entity.User;
import com.springboot.shoppingcart.service.ShoppingCartService;

@RestController
@EnableAutoConfiguration
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping(value = "user/{userId}/carts/{cartId}/products")
	public void addProduct(@RequestBody JsonNode jsonNode, @PathVariable int userId, @PathVariable int cartId) {
		System.out.println("Control has come to Controller");
		Logger.getLogger("Method adding a product into the cart :: Based on Cart id");
		ObjectMapper object = new ObjectMapper();
		Book bookRQ = null;
		Apparal apparalRQ = null;

		if (jsonNode.has("author")) {
			bookRQ = object.convertValue(jsonNode, Book.class);
		} else if (jsonNode.has("brand")) {
			apparalRQ = object.convertValue(jsonNode, Apparal.class);
		}

		Cart cart = new Cart();
		cart.setCartId(cartId);
		User user = new User();
		user.setUserId(userId);
		cart.setUser(user);

		if (bookRQ != null) {
			bookRQ.setCart(cart);
			bookRQ.setQuantity(1);
		} else if (apparalRQ != null) {
			apparalRQ.setCart(cart);
			apparalRQ.setQuantity(1);
		}

		boolean productSaved = false;

		List<Product> products = shoppingCartService.getAllProducts(cartId);
		if (products == null || products.isEmpty()) {
			if (jsonNode.has("author")) {
				shoppingCartService.addProduct(bookRQ);
			} else {
				shoppingCartService.addProduct(apparalRQ);
			}
		}

		for (Product product : products) {
			if (jsonNode.has("author") && product.getProductName().equalsIgnoreCase("book")) {
				Book bookObj = (Book) product;
				if (bookObj.getProductId() == bookRQ.getProductId()) {
					bookRQ.setQuantity(bookObj.getQuantity() + 1);
					shoppingCartService.addProduct(bookRQ);
					productSaved = true;
				}
			} else if (jsonNode.has("brand") && product.getProductName().equalsIgnoreCase("apparal")) {
				Apparal apparalObj = (Apparal) product;
				if (apparalObj.getProductId() == apparalRQ.getProductId()) {
					apparalRQ.setQuantity(apparalObj.getQuantity() + 1);
					shoppingCartService.addProduct(apparalRQ);
				}
			}
			if (jsonNode.has("author") && productSaved == false) {
				System.out.println("Control here to save book");
				shoppingCartService.addProduct(bookRQ);
			} else if (jsonNode.has("apparal") && productSaved == false) {
				System.out.println("Control here to save book");
				shoppingCartService.addProduct(apparalRQ);

			}
		}

	}

	@GetMapping(value = "cart/{cartId}/viewCartTotal")
	public float getCartTotal(@PathVariable int cartId) {
		System.out.println("Control has reached here to caculate cart total");
		float totalCartPrice = shoppingCartService.generateCartTotal(cartId);
		return totalCartPrice;
	}
	
	@PutMapping(value="cart/{cartId}/update/{quantity}")
	public void updateCart(@PathVariable int cartId,
			@PathVariable int quantity) {
		Cart cart = new Cart();
		cart.setCartId(cartId);
		
		if(quantity <= 0)
			shoppingCartService.emptyCart();
			
	}
	
	@DeleteMapping(value="cart/{cartId}/products/{productId}")
	public void deleteAllProductFromCart(@PathVariable int cartId) {
		System.out.println("Control here to delete all products from cart");
		shoppingCartService.removeAllProducts(cartId);
	}
	
	public void deleteParticularProductFromCart(@PathVariable int cartId) {
		shoppingCartService.removeProduct(cartId);
	}

}
