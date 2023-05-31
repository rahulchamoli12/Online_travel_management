package com.masai.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer routeId;
	private String routeFrom;
	private String routeTo;
	private LocalDateTime arrival;
	private LocalDateTime departure;
	private LocalDate dateOfJourney;
	private String pickUpPoint;
	private double fare;

	// Relationship mappings
	@OneToMany(mappedBy = "route")
	private List<Bus> buses;

}
