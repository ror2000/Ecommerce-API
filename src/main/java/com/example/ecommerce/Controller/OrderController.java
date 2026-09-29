package com.example.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public List<Orders> getUserOrders(@PathVariable long userId){
		return orderService.getUserOrders(userId);
	}

	@GetMapping("/{id}")
	public Orders getOrderById(@PathVariable long id){
		return orderService.getOrderById(id);
	}

	@PostMapping
	public String createOrder(@RequestBody Users user){
		return orderService.createOrder(user);
	}

	@PutMapping("/{id}/status")
	public Orders updateOrderStatus(@PathVariable long id, @RequestBody Orders order) {
		return orderService.updateOrderStatus(id, order);
	}

	@PutMapping("/{id}/cancel")
	public Orders cancelOrder(@PathVariable long id){
		return orderService.cancelOrder(id);
	}
}
