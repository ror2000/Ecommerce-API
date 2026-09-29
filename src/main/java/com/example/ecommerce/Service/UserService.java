package com.example.ecommerce.Service;

import java.util.List;

import com.example.ecommerce.Entity.Users;
import com.example.ecommerce.Repository.UsersRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private UsersRepository repo;
	private PasswordEncoder passwordEncoder;

	public UserService(UsersRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public List<Users> getAllUsers(){
		return repo.findAll();
	}
	
	public Users getById(long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public Users addUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public Users updateUser(long id, Users user) {
		Users use = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		
		use.setEmail(user.getEmail());
		use.setName(user.getName());
		if(user.getPassword() != null) {
			use.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		use.setRole(user.getRole());
		return repo.save(use);
	}
	
	public String deleteUser(long id) {
		Users use = repo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		repo.delete(use);
		return "The User with id = "+id+" Deleted successfully";
	}
}
