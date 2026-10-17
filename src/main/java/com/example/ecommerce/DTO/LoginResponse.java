package com.example.ecommerce.DTO;

public class LoginResponse {
	
	private String token ;
	private long userId ;
	
	public LoginResponse() {
		super();
	}

	public LoginResponse(String token, long userId) {
		super();
		this.token = token;
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", userId=" + userId + "]";
	}
	
	
}
