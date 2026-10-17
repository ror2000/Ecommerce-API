package com.example.ecommerce.Service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.Entity.Category;
import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Repository.CategoryRepository;
import com.example.ecommerce.Repository.ProductRepository;
import java.util.List;

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
		prod.setPrice(product.getPrice());
		prod.setStock(product.getStock());
		return repo.save(prod);
	}
	
	public String deleteProduct(long id) {
		Product prod = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		repo.delete(prod);
		return "The Product with id = "+id+" Deleted successfully";
	}
}
