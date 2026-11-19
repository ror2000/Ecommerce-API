package com.example.ecommerce.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDTO {
	
	private long id;
    private LocalDateTime order_date;
    private BigDecimal total;
    private String status;
    
	public OrderDTO() {
		super();
	}

	public OrderDTO(long id, LocalDateTime order_date, BigDecimal total, String status) {
		super();
		this.id = id;
		this.order_date = order_date;
		this.total = total;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDateTime order_date) {
		this.order_date = order_date;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
