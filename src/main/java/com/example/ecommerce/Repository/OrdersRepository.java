package com.example.ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.Entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>  {
	List<Orders> findByUserId(long userId);
}
