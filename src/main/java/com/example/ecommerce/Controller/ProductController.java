package com.example.ecommerce.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.Entity.Product;
import com.example.ecommerce.Service.CloudinaryService;
import com.example.ecommerce.Service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	private final CloudinaryService cloudinaryService;

	public ProductController(ProductService productService, CloudinaryService cloudinaryService) {
		super();
		this.productService = productService;
		this.cloudinaryService = cloudinaryService;
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
	public Product addProduct(@Valid @RequestBody Product product){
	    return productService.addProduct(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable long id, @Valid @RequestBody Product product){
	    return productService.updateProduct(id, product);
	}
	
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable long id){
	    return productService.deleteProduct(id);
	}
	
	@PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
		return cloudinaryService.uploadImage(file);
	}
}
