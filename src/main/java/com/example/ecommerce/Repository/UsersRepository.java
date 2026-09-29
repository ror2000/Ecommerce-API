package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByEmail(String email);
}
