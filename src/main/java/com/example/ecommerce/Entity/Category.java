package com.example.ecommerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank(message = "Name cannot be blank")
	private String name;
	private String description;
	
	private String name_en;
	private String name_ar;

	private String description_en;
	private String description_ar;
	
	public Category() {
		super();
	}

	public Category(long id, @NotBlank(message = "Name cannot be blank") String name, String description,
			String name_en, String name_ar, String description_en, String description_ar) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
		return "Category [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
