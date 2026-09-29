package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
