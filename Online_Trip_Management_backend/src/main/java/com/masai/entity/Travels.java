package com.masai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Travels {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer travelId;
	private String travelName;
	private String agentName;
	private String address;
	private String contact;
	
	
	
}
