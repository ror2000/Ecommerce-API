package com.example.ecommerce.Service;

import java.util.List;

import com.example.ecommerce.Entity.Category;
import com.example.ecommerce.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

	private CategoryRepository repo;

	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	public List<Category> getAllCategories(){
		return repo.findAll();
	}
	
	public Category getById(long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
	}
	
	public Category addCategory(Category category) {
		return repo.save(category);
	}
	
	public Category updateCategory(long id, Category category) {
		Category ctg = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
		
		ctg.setName(category.getName());
		ctg.setDescription(category.getDescription());
		return repo.save(ctg);
	}
	
	public String deleteCategory(long id) {
		Category ctg = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
		repo.delete(ctg);
		return "The Category with id = "+id+" Deleted successfully";
	}
}
