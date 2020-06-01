package com.springboot.shoppingcart.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.shoppingcart.entity.Book;
import com.springboot.shoppingcart.entity.Product;
import com.springboot.shoppingcart.repository.CartRepository;
import com.springboot.shoppingcart.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public void addBook(Book book) {
		shoppingCartRepository.save(book);
	}

	@Override
	public void addProduct(Product product) {
		shoppingCartRepository.save(product);
	}

	@Override
	public List<Product> getAllProducts(int cartId) {
		List<Product> products = new ArrayList<>();
		products = shoppingCartRepository.findByCartId(cartId);
		if (products != null) {
			for (Product product : products) {
				System.out.println("showing product = " + product);
			}
		}
		return products;
	}

	@Override
	public int findProductQuantityInCart(int cartId) {
		/*
		 * int prodQuantity = 0; prodQuantity =
		 * shoppingCartRepository.findByCartId(cartId);
		 * System.out.println("product qty in cart = "+ );
		 */
		return 0;
	}

	@Override
	public void removeProduct(int productId) {
		shoppingCartRepository.deleteById(productId);
	}

	@Override
	public void removeAllProducts(int cartId) {
		List<Product> products = getAllProducts(cartId);
		if (products != null) {
			for (Product product : products) {
				shoppingCartRepository.deleteById(product.getProductId());
				System.out.println("all products deleted");
			}
		}
	}

	@Override
	public void emptyCart() {
		shoppingCartRepository.deleteAll();
	}

	@Override
	public float generateCartTotal(int cartId) {
		List<Product> products = getAllProducts(cartId);
		float sum = 0;
		sum = calculateTotalCartPrice(sum, products);
		return sum;

	}

	private float calculateTotalCartPrice(float sum, List<Product> products) {
		if (null != products) {
			for (Product product : products) {
				if (product != null) {
					sum = sum + (product.getProductId() * product.getQuantity());
				}
			}
		}
		return sum;
	}

	@Override
	public void emptyTheCart(int cartId) {
		cartRepository.deleteAll();
	}

}
