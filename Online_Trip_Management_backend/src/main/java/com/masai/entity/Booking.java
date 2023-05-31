package com.masai.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	private String bookingTitle;
	private String bookingType;
	private String description;
	private Integer numberOfPeople;
	private LocalDateTime bookingDate;
	
	//relationships
	
	// Many-to-one relationship with Customer
    @ManyToOne
    @JoinColumn(name = "userId")
    private Customer customer;
	
    // Many-to-one relationship with Package
    @ManyToOne
    @JoinColumn(name = "packageId")
    private Package tourPackage;
    
    // Relationship mappings
    @OneToOne(mappedBy = "booking")
    private TicketDetails ticketDetails;
    
    // One-to-one relationship with PaymentDetails
    @OneToOne(mappedBy = "booking")
    private PaymentDetails paymentDetails;
    
    @ManyToOne
    @JoinColumn(name = "busId")
    private Bus buses;
	
}
