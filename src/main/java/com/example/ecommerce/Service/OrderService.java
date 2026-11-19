package com.example.ecommerce.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ecommerce.DTO.OrderDTO;
import com.example.ecommerce.DTO.OrderItemDTO;
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
	
	public List<OrderDTO> getUserOrders(long id, String language) {
	    return orderRepo.findByUserId(id).stream().map(order -> toDTO(order, language)).collect(Collectors.toList());
	}
	
	public OrderDTO getOrderById(long id, String language) {
	    Orders order = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	    return toDTO(order, language);
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
	
    public OrderDTO updateOrderStatus(long id, Orders order, String language) {
        Orders oldOrder = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        oldOrder.setStatus(order.getStatus());
        Orders savedOrder = orderRepo.save(oldOrder);
        return toDTO(savedOrder, language);
    }
	
    public OrderDTO cancelOrder(long id, String language) {
        Orders oldOrder = orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        oldOrder.setStatus("Cancelled");
        Orders savedOrder = orderRepo.save(oldOrder);
        return toDTO(savedOrder, language);
    }
    
    public List<OrderItemDTO> getByOrderId(long orderId){
    	return orderItemRepo.findByOrderId(orderId).stream().map(item -> new OrderItemDTO(item.getId(),
    			item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getImage_url(),
    			item.getQuantity(), item.getPrice())).toList();
    }
    
    private OrderDTO toDTO(Orders order, String language) {
        String status = order.getStatus();

        if ("ar".equalsIgnoreCase(language)) {
            switch (status) {
                case "New":
                    status = "جديد";
                    break;
                case "Cancelled":
                    status = "ملغي";
                    break;
                case "Processing":
                    status = "قيد التجهيز";
                    break;
                case "Shipped":
                    status = "تم الشحن";
                    break;
                case "Delivered":
                    status = "تم التوصيل";
                    break;
            }
        }

        return new OrderDTO(order.getId(), order.getOrder_date(), order.getTotal(), status);
    }
}
