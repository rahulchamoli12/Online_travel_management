package com.masai.service;

import com.masai.dto.BookingDTO;
import com.masai.entity.Booking;
import com.masai.exception.BookingException;
import com.masai.exception.LoginException;

public interface BookingService {
	
	public Booking makeBooking(String uuid , BookingDTO bookingdto) throws BookingException,LoginException;
	
}
