package com.masai.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.dto.BookingDTO;
import com.masai.entity.Booking;
import com.masai.entity.CurrentUserSession;
import com.masai.exception.BookingException;
import com.masai.exception.LoginException;
import com.masai.repository.BookingRepository;
import com.masai.repository.SessionRepository;

public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookRepo;
	
	@Autowired
	private SessionRepository sessRepo;

	@Override
	public Booking makeBooking(String sessionId ,BookingDTO bookingdto) throws BookingException, LoginException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if(cus != null) throw new LoginException("user not logged in");
		
		
		return null;
	}

}
