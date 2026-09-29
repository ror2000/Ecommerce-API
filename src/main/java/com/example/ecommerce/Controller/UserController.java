package com.example.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Entity.Users;
import com.example.ecommerce.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public List<Users> getAllUsers(){
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public Users getById(@PathVariable long id){
		return userService.getById(id);
	}

	@PostMapping
	public Users addUser(@RequestBody Users user){
		return userService.addUser(user);
	}

	@PutMapping("/{id}")
	public Users updateUser(@PathVariable long id, @RequestBody Users user){
		return userService.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable long id){
		return userService.deleteUser(id);
	}
}
