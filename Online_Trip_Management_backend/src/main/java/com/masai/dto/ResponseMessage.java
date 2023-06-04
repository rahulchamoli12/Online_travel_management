package com.masai.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseMessage {

	private LocalDateTime responseTime = LocalDateTime.now();
	private String message;

	public ResponseMessage(String message) {
		this.message = message;
	}

}
