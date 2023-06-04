package com.masai.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
	private LocalDate dateOfJourney;
	private double fare;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "route_id")
	private Route route;

	// Relationship mappings
	@JsonIgnore
	@OneToMany(mappedBy = "bus")
	private List<Booking> bookings = new ArrayList<>();

	@Override
	public String toString() {
		return "Bus [busId=" + busId + ", busType=" + busType + ", busNumber=" + busNumber + ", capacity=" + capacity
				+ "]";
	}
	
	

}
