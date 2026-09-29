package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByUserId(long userId);
}
