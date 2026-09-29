package com.example.ecommerce.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.Entity.Cart;
import com.example.ecommerce.Entity.CartItem;
import com.example.ecommerce.Entity.OrderItem;
import com.example.ecommerce.Entity.Orders;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Entity.Users;
import com.example.ecommerce.Repository.CartItemRepository;
import com.example.ecommerce.Repository.CartRepository;
import com.example.ecommerce.Repository.OrderItemRepository;
import com.example.ecommerce.Repository.OrdersRepository;
import com.example.ecommerce.Repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
	
	private OrderItemRepository orderItemRepo;
	private OrdersRepository orderRepo;
	private CartRepository cartRepo;
	private CartItemRepository cartItemRepo;
	private ProductRepository productRepo;
	
	public OrderService(OrderItemRepository orderItemRepo, OrdersRepository orderRepo, CartRepository cartRepo, CartItemRepository cartItemRepo, ProductRepository productRepo) {
		this.orderItemRepo = orderItemRepo;
		this.orderRepo = orderRepo;
		this.cartRepo = cartRepo;
		this.cartItemRepo = cartItemRepo;
		this.productRepo = productRepo;
	}
	
	public List<Orders> getUserOrders(long id){
		List<Orders> orders = orderRepo.findByUserId(id);
		return orders;
	}
	
    public Orders getOrderById(long id) {
    	Orders order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    	return order;
    }
	
    @Transactional
    public String createOrder(Users user) {
    	Cart cart = cartRepo.findByUserId(user.getId());
    	if(cart == null) {
    		throw new RuntimeException("Cart not found");
    	}
    	List<CartItem> cartitem = cartItemRepo.findByCartId(cart.getId());
    	if(cartitem.isEmpty()) {
    		throw new RuntimeException("Cart is Empty");
    	}
    	Orders order = new Orders();
    	order.setUser(user);
    	order.setOrder_date(LocalDateTime.now());
    	order.setStatus("New");
    	orderRepo.save(order);
    	BigDecimal total = BigDecimal.ZERO;
    	for(CartItem item : cartitem) {
    		Product product = productRepo.findById(item.getProduct().getId()).orElseThrow(() -> new RuntimeException("Product not found"));
    		if(product.getStock() < item.getQuantity()) {
    			throw new RuntimeException("Not enough stock");
    		}
    		OrderItem orderitem = new OrderItem();
    		orderitem.setOrder(order);
    		orderitem.setPrice(item.getPrice());
    		orderitem.setProduct(product);
    		orderitem.setQuantity(item.getQuantity());
    		
    		BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
    		total = total.add(itemTotal);
    		
    		product.setStock(product.getStock() - item.getQuantity());
    		orderItemRepo.save(orderitem);
    		productRepo.save(product);
    	}
    	order.setTotal(total);
    	orderRepo.save(order);
    	cartItemRepo.deleteAll(cartitem);
    	cart.setTotal(BigDecimal.ZERO);
    	cartRepo.save(cart);
    	return "Order created successfully";
    }
	
    public Orders updateOrderStatus(long id, Orders order) {
    	Orders oldOrder = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    	oldOrder.setStatus(order.getStatus());
    	return orderRepo.save(oldOrder);
    }
	
    public Orders cancelOrder(long id) {
    	Orders oldOrder = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    	oldOrder.setStatus("Cancelled");
    	return orderRepo.save(oldOrder);
    }
}
