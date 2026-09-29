package com.example.ecommerce.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}
	
	@GetMapping
	public List<Product> getAllProducts() {
	    return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getById(@PathVariable long id){
	    return productService.getById(id);
	}
	
	@PostMapping
	public Product addProduct(@RequestBody Product product){
	    return productService.addProduct(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable long id, @RequestBody Product product){
	    return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable long id){
	    return productService.deleteProduct(id);
	}
}
