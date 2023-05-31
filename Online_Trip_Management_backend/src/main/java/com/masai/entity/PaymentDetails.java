package com.masai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class PaymentDetails {

	public enum PaymentType{
		CREDIT_CARD,DEBIT_CARD,UPI,NET_BANKING
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer PaymentId;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	private double amount;

	// One-to-one relationship with Booking
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@ManyToOne
	@JoinColumn(name = "report_id")
	private Report report;

}
