package com.masai.entity;

import java.util.List;

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
    @OneToMany(mappedBy = "tourPackage")
    private List<Booking> bookings;
    
	// Relationship mappings
    @ManyToMany
    @JoinTable(
        name = "package_hotel",
        joinColumns = @JoinColumn(name = "package_id"),
        inverseJoinColumns = @JoinColumn(name = "hotel_id")
    )
    private List<Hotel> hotels;
	
    @ElementCollection
    @Embedded
    @JoinTable(
            name = "feedback",
            joinColumns = @JoinColumn(name = "package_id")
        )
    private List<Feedback> feedbacks;
	
}
