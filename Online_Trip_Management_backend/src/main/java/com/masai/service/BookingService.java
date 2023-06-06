package com.masai.service;

import java.util.List;

import com.masai.dto.BookingDTO;
import com.masai.entity.Booking;
import com.masai.exception.AdminException;
import com.masai.exception.BookingException;
import com.masai.exception.BusException;
import com.masai.exception.CustomerException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.exception.RouteException;
import com.masai.exception.UserException;

public interface BookingService {
	
	
	public Booking makeBooking(String uuid , BookingDTO bookingdto) throws BookingException,LoginException,UserException, RouteException, PackageException, CustomerException, BusException, HotelException;
	
	public Booking cancelBooking(String sessionId, Integer bookingId) throws BookingException, LoginException, CustomerException;
	
	public Booking viewBooking(String sessionId,Integer bookingId) throws BookingException, LoginException;
	
	public List<Booking> viewAllBooking(String sessionId) throws BookingException, LoginException, AdminException;
	

	
}
