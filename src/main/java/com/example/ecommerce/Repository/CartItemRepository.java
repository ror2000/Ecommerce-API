package com.example.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long>  {
	CartItem findByCartIdAndProductId(long cartId, long productId);
    List<CartItem> findByCartId(long cartId);
}
