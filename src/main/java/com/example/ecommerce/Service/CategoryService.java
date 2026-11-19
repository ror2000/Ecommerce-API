package com.example.ecommerce.Service;

import java.util.List;

import com.example.ecommerce.Entity.Category;
import com.example.ecommerce.Repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import com.example.ecommerce.DTO.CategoryDTO;

@Service
public class CategoryService {

	private CategoryRepository repo;

	public CategoryService(CategoryRepository repo) {
		this.repo = repo;
	}
	
	public List<Category> getAllCategories(){
		return repo.findAll();
	}
	
	public Category getByName(String name) {
		return repo.findByNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Category not found"));
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
		ctg.setName_en(category.getName_en());
		ctg.setName_ar(category.getName_ar());
		ctg.setDescription_en(category.getDescription_en());
		ctg.setDescription_ar(category.getDescription_ar());
		return repo.save(ctg);
	}
	
	public String deleteCategory(long id) {
		Category ctg = repo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
		repo.delete(ctg);
		return "The Category with id = "+id+" Deleted successfully";
	}
	
	private CategoryDTO toDTO(Category category, String language) {
	    String name = "ar".equalsIgnoreCase(language)
	            ? category.getName_ar() : category.getName_en();

	    String description = "ar".equalsIgnoreCase(language)
	            ? category.getDescription_ar() : category.getDescription_en();

	    if (name == null || name.isBlank()) {
	        name = category.getName();
	    }

	    if (description == null || description.isBlank()) {
	        description = category.getDescription();
	    }

	    return new CategoryDTO(category.getId(), name, description);
	}
	
	public List<CategoryDTO> getAllCategories(String language) {
	    return repo.findAll().stream()
	            .map(category -> toDTO(category, language))
	            .collect(Collectors.toList());
	}
	
	public CategoryDTO getById(long id, String language) {
	    Category category = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Category not found"));

	    return toDTO(category, language);
	}
}
