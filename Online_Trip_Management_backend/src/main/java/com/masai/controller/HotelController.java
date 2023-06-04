package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Hotel;
import com.masai.exception.AdminException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.service.HotelService;

@RestController("/hotels")
@CrossOrigin("*")
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@PostMapping("/registerHotel/{sessionId}")
	public ResponseEntity<Hotel> registerNewHotel(@PathVariable String sessionId, @RequestBody Hotel hotel)
			throws LoginException, AdminException, HotelException {

		Hotel newHotel = hotelService.registerNewHotel(sessionId, hotel);
		return new ResponseEntity<>(newHotel, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateHotel/{sessionId}")
	public ResponseEntity<Hotel> updateHotelDetails(@PathVariable String sessionId, @RequestBody Hotel hotel)
			throws LoginException, AdminException, HotelException {
		
		Hotel existingHotel = hotelService.updateHotelDetails(sessionId, hotel);
		return new ResponseEntity<>(existingHotel, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteHotel/{sessionId}/{hotelId}")
	public ResponseEntity<Hotel> deleteHotelDetails(@PathVariable String sessionId, @PathVariable Integer hotelId)
			throws LoginException, AdminException, HotelException {
		
		Hotel hotel = hotelService.deleteHotelDetails(sessionId, hotelId);
		return new ResponseEntity<>(hotel, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/hotelDetailsById/{sessionId}/{hotelId}")
	public ResponseEntity<Hotel> getHotelByHotelId(@PathVariable String sessionId, @PathVariable Integer hotelId)
			throws LoginException, AdminException, HotelException {
		
		Hotel hotel = hotelService.getHotelByHotelId(sessionId, hotelId);
		return new ResponseEntity<>(hotel, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/hotelsDetailsByName/{sessionId}/{hotelName}")
	public ResponseEntity<List<Hotel>> getHotelByHotelName(@PathVariable String sessionId, @PathVariable String hotelName)
			throws LoginException, AdminException, HotelException {
		
		List<Hotel> hotels = hotelService.getHotelsByHotelName(sessionId, hotelName);
		return new ResponseEntity<>(hotels, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allHotelsDetails/{sessionId}")
	public ResponseEntity<List<Hotel>> getAllHotelsDetails(@PathVariable String sessionId)
			throws LoginException, AdminException, HotelException {
		
		List<Hotel> hotels = hotelService.getAllHotels(sessionId);
		return new ResponseEntity<>(hotels, HttpStatus.ACCEPTED);
	}
	
	
	

}
