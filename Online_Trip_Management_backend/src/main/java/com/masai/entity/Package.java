package com.masai.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
public class Package {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer packageId;
	private String packageName;
	private String packageDescription;
	private String packageType;
	private Double packagePrice;
	
	// relations
	
	 // One-to-many relationship with Booking
	@JsonIgnore
    @OneToMany(mappedBy = "tourPackage")
    private List<Booking> bookings = new ArrayList<>();
    
	// Relationship mappings
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "package_hotel",
        joinColumns = @JoinColumn(name = "package_id"),
        inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> hotels = new ArrayList<>();
	
    @ElementCollection
    @Embedded
    @JoinTable(
            name = "feedback",
            joinColumns = @JoinColumn(name = "packageId")
        )
    private List<Feedback> feedbacks = new ArrayList<>();
    
    
	
}
