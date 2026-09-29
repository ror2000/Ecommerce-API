package com.example.ecommerce.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Entity.Users;
import com.example.ecommerce.Repository.UsersRepository;
import com.example.ecommerce.Security.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UsersRepository userRepo;
	private PasswordEncoder passwordEncoder;
	private JwtService jwtService;
	
	public AuthController(UsersRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Users user){
		Users existingUser = userRepo.findByEmail(user.getEmail());
		if(existingUser == null) {
			throw new RuntimeException("User not found");
		}
		
		if(!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
			throw new RuntimeException("Invalid password");
		}
		
		String token = jwtService.generateToken(existingUser.getEmail());
		
		return ResponseEntity.ok(token);
	}
}
