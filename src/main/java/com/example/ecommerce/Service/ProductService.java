package com.example.ecommerce.Service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.Entity.Category;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Repository.CategoryRepository;
import com.example.ecommerce.Repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.DTO.CategoryDTO;

@Service
public class ProductService {

	private ProductRepository repo;
	private final CategoryRepository categoryRepository;

	public ProductService(ProductRepository repo, CategoryRepository categoryRepository) {
		this.repo = repo;
		this.categoryRepository = categoryRepository;
	}
	
	public List<Product> getAllProducts(){
		return repo.findAll();
	}
	
	public Product getById(long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	
	public Product addProduct(Product product) {
		return repo.save(product);
	}

	public Product addProductByCategoryName(Product product, String categoryName) {
	    Category category = categoryRepository.findByNameIgnoreCase(categoryName)
	            .orElseThrow(() -> new RuntimeException("Category not found: " + categoryName));
	
	    product.setCategory(category);
	    return repo.save(product);
	}
	
	public Product updateProduct(long id, Product product) {
		Product prod = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		
		prod.setCategory(product.getCategory());
		prod.setDescription(product.getDescription());
		prod.setImage_url(product.getImage_url());
		prod.setName(product.getName());
		prod.setName_en(product.getName_en());
		prod.setName_ar(product.getName_ar());
		prod.setDescription_en(product.getDescription_en());
		prod.setDescription_ar(product.getDescription_ar());
		prod.setPrice(product.getPrice());
		prod.setStock(product.getStock());
		return repo.save(prod);
	}
	
	public String deleteProduct(long id) {
		Product prod = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		repo.delete(prod);
		return "The Product with id = "+id+" Deleted successfully";
	}
	
	private ProductDTO toDTO(Product product, String language) {
	    boolean isArabic = "ar".equalsIgnoreCase(language);

	    String name = isArabic ? product.getName_ar() : product.getName_en();
	    String description = isArabic
	            ? product.getDescription_ar() : product.getDescription_en();

	    if (name == null || name.isBlank()) {
	        name = product.getName();
	    }

	    if (description == null || description.isBlank()) {
	        description = product.getDescription();
	    }

	    Category category = product.getCategory();

	    CategoryDTO categoryDTO = null;

	    if (category != null) {
	        String categoryName = isArabic
	                ? category.getName_ar() : category.getName_en();

	        String categoryDescription = isArabic
	                ? category.getDescription_ar() : category.getDescription_en();

	        if (categoryName == null || categoryName.isBlank()) {
	            categoryName = category.getName();
	        }

	        if (categoryDescription == null || categoryDescription.isBlank()) {
	            categoryDescription = category.getDescription();
	        }

	        categoryDTO = new CategoryDTO(
	                category.getId(), categoryName, categoryDescription
	        );
	    }

	    return new ProductDTO(
	            product.getId(),
	            name,
	            description,
	            product.getPrice(),
	            product.getStock(),
	            product.getImage_url(),
	            categoryDTO
	    );
	}
	
	public List<ProductDTO> getAllProducts(String language) {
	    return repo.findAll().stream()
	            .map(product -> toDTO(product, language))
	            .collect(Collectors.toList());
	}
	
	public ProductDTO getById(long id, String language) {
	    Product product = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Product not found"));

	    return toDTO(product, language);
	}
}
