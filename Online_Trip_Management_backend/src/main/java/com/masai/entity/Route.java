package com.masai.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
	private String pickUpPoint;
	

	// Relationship mappings
	@OneToMany(mappedBy = "route",cascade = CascadeType.ALL)
	private List<Bus> buses = new ArrayList<>();

}
