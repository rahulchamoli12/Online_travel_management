package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.dto.BookingDTO;
import com.masai.entity.Booking;
import com.masai.entity.Bus;
import com.masai.exception.AdminException;
import com.masai.exception.BookingException;
import com.masai.exception.BusException;
import com.masai.exception.CustomerException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.exception.RouteException;
import com.masai.exception.UserException;
import com.masai.repository.BookingRepository;
import com.masai.service.BookingService;

import jakarta.validation.Valid;

@RestController("/bookings")
@CrossOrigin("*")
public class BookingController {
	
	@Autowired
	private BookingService bookService;
	
	@PostMapping("/make/{sessionId}")
	public ResponseEntity<Booking> addBookingController(@Valid @RequestBody BookingDTO bookingdto, @PathVariable String sessionId) throws LoginException, BusException, BookingException, UserException, RouteException, PackageException, CustomerException, HotelException{
		Booking book = bookService.makeBooking(sessionId, bookingdto);
		return new ResponseEntity<>(book, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/cancel/{sessionId}")
	public ResponseEntity<Booking> cancelBookingController(@Valid  @PathVariable String sessionId,@RequestParam("bookingId") Integer bookingId) throws LoginException, BusException, BookingException, UserException, RouteException, PackageException, CustomerException{
		Booking book = bookService.cancelBooking(sessionId, bookingId);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@GetMapping("/view/{sessionId}")
	public ResponseEntity<Booking> viewBookingController(@Valid  @PathVariable String sessionId,@RequestParam("bookingId") Integer bookingId) throws LoginException, BusException, BookingException, UserException, RouteException, PackageException, CustomerException{
		Booking book = bookService.viewBooking(sessionId, bookingId);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@GetMapping("/viewall/{sessionId}")
	public ResponseEntity<List<Booking>> viewAllBookingController(@Valid  @PathVariable String sessionId) throws LoginException, BusException, BookingException, UserException, RouteException, PackageException, CustomerException, AdminException{
		List<Booking> bookings = bookService.viewAllBooking(sessionId);
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}
	
}
