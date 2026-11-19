package com.example.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.DTO.OrderDTO;
import com.example.ecommerce.DTO.OrderItemDTO;
import com.example.ecommerce.Entity.Orders;
import com.example.ecommerce.Entity.Users;
import com.example.ecommerce.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}
	
	@GetMapping("/user/{userId}")
	public List<OrderDTO> getUserOrders(@PathVariable long userId, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
	    return orderService.getUserOrders(userId, language);
	}

	@GetMapping("/{id}")
	public OrderDTO getOrderById(@PathVariable long id, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
	    return orderService.getOrderById(id, language);
	}

	@PostMapping
	public String createOrder(@RequestBody Users user){
		return orderService.createOrder(user);
	}

	@PutMapping("/{id}/status")
	public OrderDTO updateOrderStatus(@PathVariable long id, @RequestBody Orders order, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
	    return orderService.updateOrderStatus(id, order, language);
	}

	@PutMapping("/{id}/cancel")
	public OrderDTO cancelOrder(@PathVariable long id, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
	    return orderService.cancelOrder(id, language);
	}
	
	@GetMapping("/{id}/items")
	public List<OrderItemDTO> getByOrderId(@PathVariable long id){
		return orderService.getByOrderId(id);
	}
}
