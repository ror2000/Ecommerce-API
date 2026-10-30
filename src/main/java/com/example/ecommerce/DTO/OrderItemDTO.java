package com.example.ecommerce.DTO;

import java.math.BigDecimal;

public class OrderItemDTO {
	
	private long id;
	private long productId;
	private String productName;
	private String image_url;
	private int quantity;
	private BigDecimal price;
	
	public OrderItemDTO(long id, long productId, String productName, String image_url, int quantity, BigDecimal price) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.image_url = image_url;
		this.quantity = quantity;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
