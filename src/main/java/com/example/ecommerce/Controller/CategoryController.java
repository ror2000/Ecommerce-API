package com.example.ecommerce.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.DTO.CategoryDTO;
import com.example.ecommerce.Entity.Category;
import com.example.ecommerce.Service.CategoryService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
	
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	
	@GetMapping
	public List<CategoryDTO> getAllCategories(@RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
	    return categoryService.getAllCategories(language);
	}

	@GetMapping("/{id}")
	public CategoryDTO getById(@PathVariable long id, @RequestHeader(value = "Accept-Language", defaultValue = "en") String language) {
	    return categoryService.getById(id, language);
	}

	@PostMapping
	public Category addCategory(@Valid @RequestBody Category category){
		return categoryService.addCategory(category);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable long id, @Valid @RequestBody Category category){
		return categoryService.updateCategory(id, category);
	}

	@DeleteMapping("/{id}")
	public String deleteCategory(@PathVariable long id){
		return categoryService.deleteCategory(id);
	}
}
