package com.masai.dto;

import java.time.LocalDate;

import com.masai.entity.PaymentDetails.PaymentType;

import lombok.Data;

@Data
public class BookingDTO {
	
	private String bookingTitle;
	private String bookingType;
	private String description;
	private Integer numberOfPeople;
	private LocalDate startDateJourney;
	private LocalDate endDateJourney;
	private String routeFrom;
	private String routeTo;
	private Integer user_id;
	private Integer package_id;
	private Integer bus_id;
	private Integer hotel_id;
	private String paymentType;
	private double amount;
	
}
