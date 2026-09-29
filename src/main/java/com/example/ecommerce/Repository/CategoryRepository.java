package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
