package com.example.ecommerce.DTO;

import java.math.BigDecimal;

public class ProductDTO {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String image_url;
    private CategoryDTO category;

    public ProductDTO(long id, String name, String description, BigDecimal price, int stock, String image_url, CategoryDTO category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image_url = image_url;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getImage_url() {
        return image_url;
    }

    public CategoryDTO getCategory() {
        return category;
    }
}