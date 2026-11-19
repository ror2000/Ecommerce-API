package com.example.ecommerce.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "Name cannot be blank")
	private String name;
	private String description;
	@NotNull(message = "Price is required")
	@DecimalMin(value = "0.0", message = "Price cannot be negative")
	private BigDecimal price;
	@Min(value = 0, message = "Stock cannot be negative")
	private int stock;
	private String image_url;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	private String name_en;
	private String name_ar;

	private String description_en;
	private String description_ar;
	
	public Product() {
		super();
	}

	public Product(long id, @NotBlank(message = "Name cannot be blank") String name, String description,
			@NotNull(message = "Price is required") @DecimalMin(value = "0.0", message = "Price cannot be negative") BigDecimal price,
			@Min(value = 0, message = "Stock cannot be negative") int stock, String image_url, Category category,
			String name_en, String name_ar, String description_en, String description_ar) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.image_url = image_url;
		this.category = category;
		this.name_en = name_en;
		this.name_ar = name_ar;
		this.description_en = description_en;
		this.description_ar = description_ar;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_ar() {
		return name_ar;
	}

	public void setName_ar(String name_ar) {
		this.name_ar = name_ar;
	}

	public String getDescription_en() {
		return description_en;
	}

	public void setDescription_en(String description_en) {
		this.description_en = description_en;
	}

	public String getDescription_ar() {
		return description_ar;
	}

	public void setDescription_ar(String description_ar) {
		this.description_ar = description_ar;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", stock="
				+ stock + ", image_url=" + image_url + ", category=" + category + "]";
	}
	
}
