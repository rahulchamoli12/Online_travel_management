package com.masai.entity;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{
	
	private String address;
	
	
	// One-to-many relationship with Booking
    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;
}
