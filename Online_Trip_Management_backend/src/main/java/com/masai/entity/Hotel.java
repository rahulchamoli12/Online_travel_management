package com.masai.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
public class Hotel {
	
	public enum HotelType{
		ONE_STAR,THREE_STAR,FIVE_STAR,SEVEN_STAR
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hotelId;
	private String hotelName;
	private String hotelDescription;
	
	@Enumerated(EnumType.STRING)
	private HotelType hotelType;
	private Double hotelRent;
	private String hotelAddress;
	private String isAvailable;
	
	//Relationships
	@JsonIgnore
    @ManyToMany(mappedBy = "hotels")
    private List<Package> packages = new ArrayList<>();
    
 // Relationship mappings
 	@JsonIgnore
 	@OneToMany(mappedBy = "hotel")
 	private List<Booking> bookings= new ArrayList<>();
 
    
}
