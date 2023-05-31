package com.masai.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Bus {
	
	public enum BusType{
		AC,SLEEPER,VOLVO,NORMAL
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;
	@Enumerated(EnumType.STRING)
	private BusType busType;
	private String busNumber;
	private Integer capacity;

	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;

	// Relationship mappings
	@OneToMany(mappedBy = "buses")
	private List<Booking> bookings;

}
