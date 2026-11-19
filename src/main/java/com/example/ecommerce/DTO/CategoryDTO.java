package com.example.ecommerce.DTO;

public class CategoryDTO {

    private long id;
    private String name;
    private String description;

    public CategoryDTO(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}