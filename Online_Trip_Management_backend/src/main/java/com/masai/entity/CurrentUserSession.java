package com.masai.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserSession {
	public enum Role {
		ADMIN,CUSTOMER;
	}
	
	@Id
	private Integer userId;
	private String sessionId;
	@Enumerated(EnumType.STRING)
	private Role role;
	private LocalDateTime loginTime;
}
