package com.example.ecommerce.Service;

import java.math.BigDecimal;
import java.util.List;

import com.example.ecommerce.Entity.Cart;
import com.example.ecommerce.Entity.CartItem;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Repository.CartItemRepository;
import com.example.ecommerce.Repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

	private CartRepository cartRepo;
	private CartItemRepository cartItemRepo;
	private ProductService prodService;
	
	public CartService(CartRepository cartRepo, CartItemRepository cartItemRepo,ProductService prodService) {
		this.cartRepo = cartRepo;
		this.cartItemRepo = cartItemRepo;
		this.prodService = prodService;
	}
	
	public Cart getCartByUser(long id) {
		Cart cart = cartRepo.findByUserId(id);
		if (cart == null) {
            throw new RuntimeException("Cart not found");
        }
		return cart;
	}
	
	public String addToCart(long cart_id, long prod_id) {
		Product prod = prodService.getById(prod_id);
		if(prod.getStock() <= 0) {
			return "Product didn't added to Cart";
		} else {			
			Cart cart = cartRepo.findById(cart_id).orElseThrow(() -> new RuntimeException("Cart not found"));
			CartItem cartitem = cartItemRepo.findByCartIdAndProductId(cart_id, prod_id);
			if(cartitem == null) {
				cartitem = new CartItem();

	            cartitem.setCart(cart);
	            cartitem.setProduct(prod);
	            cartitem.setQuantity(1);
	            cartitem.setPrice(prod.getPrice());
			} else {
				if (cartitem.getQuantity() >= prod.getStock()) {
			        throw new RuntimeException("Not enough stock");
			    }
			    cartitem.setQuantity(cartitem.getQuantity() + 1);
			}
			cartItemRepo.save(cartitem);
			cart.setTotal(cart.getTotal().add(prod.getPrice()));
			cartRepo.save(cart);
			return "Product added to Cart";
		}
	}
	
	public CartItem updateCartItem(long id, CartItem cartitem) {
		CartItem item = cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart Item not found"));
		if (cartitem.getQuantity() > item.getProduct().getStock()) {
		    throw new RuntimeException("Not enough stock");
		}
		
		Cart cart = cartRepo.findById(item.getCart().getId()).orElseThrow(() -> new RuntimeException("Cart not found"));

		BigDecimal oldTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

		item.setQuantity(cartitem.getQuantity());

		BigDecimal newTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

		cart.setTotal(cart.getTotal().subtract(oldTotal).add(newTotal));

		cartRepo.save(cart);
		cartItemRepo.save(item);

		return item;
	}
	
	public String removeFromCart(long id) {
		CartItem cartitem = cartItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart Item not found"));
		Cart cart = cartRepo.findById(cartitem.getCart().getId()).orElseThrow(() -> new RuntimeException("Cart not found"));
		BigDecimal price = cartitem.getPrice();
		int quantity = cartitem.getQuantity();
		cartItemRepo.delete(cartitem);
		cart.setTotal(cart.getTotal().subtract(price.multiply(BigDecimal.valueOf(quantity))));
		cartRepo.save(cart);
		return "The Cart Item with id = "+id+" Deleted successfully";
	}
	
	public Cart clearCart(long id) {
		Cart cart = cartRepo.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
		List<CartItem> cartitem = cartItemRepo.findByCartId(id);
		cartItemRepo.deleteAll(cartitem);
		cart.setTotal(BigDecimal.ZERO);
        return cartRepo.save(cart);
	}
}
