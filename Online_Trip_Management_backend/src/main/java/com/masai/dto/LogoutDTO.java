package com.masai.dto;

public class LogoutDTO {

	private String message;

	public LogoutDTO(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
