package com.example.ecommerce.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Entity.Cart;
import com.example.ecommerce.Entity.CartItem;
import com.example.ecommerce.Service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	private CartService cartService;
	
	public CartController(CartService cartService) {
		super();
		this.cartService = cartService;
	}

	@GetMapping("/{userId}")
	public Cart getCartByUser(@PathVariable long userId) {
		return cartService.getCartByUser(userId);
	}

	@PostMapping("/{cartId}/products/{productId}")
	public String addToCart(@PathVariable long cartId, @PathVariable long productId) {
		return cartService.addToCart(cartId, productId);
	}

	@PutMapping("/items/{itemId}")
	public CartItem updateCartItem(@PathVariable long itemId, @Valid @RequestBody CartItem cartitem) {
		return cartService.updateCartItem(itemId, cartitem);
	}

	@DeleteMapping("/items/{itemId}")
	public String removeFromCart(@PathVariable long itemId) {
		return cartService.removeFromCart(itemId);
	}

	@DeleteMapping("/{cartId}")
	public Cart clearCart(@PathVariable long cartId) {
		return cartService.clearCart(cartId);
	}
}
