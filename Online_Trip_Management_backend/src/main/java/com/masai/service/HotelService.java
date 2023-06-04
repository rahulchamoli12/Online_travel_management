package com.masai.service;

import java.util.List;

import com.masai.entity.Hotel;
import com.masai.exception.AdminException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;

/*
 * @Author Aman_Maurya
 * 
 * All Necessary Method for hotel Service
 * 
 */

public interface HotelService {

	public Hotel registerNewHotel(String sessionId, Hotel hotel) throws LoginException, AdminException, HotelException;

	public Hotel updateHotelDetails(String sessionId, Hotel hotel)
			throws LoginException, AdminException, HotelException;

	public Hotel deleteHotelDetails(String sessionId, Integer hotelId)
			throws LoginException, AdminException, HotelException;

	public Hotel getHotelByHotelId(String sessionId, Integer hotelId)
			throws LoginException, AdminException, HotelException;

	public List<Hotel> getHotelsByHotelName(String sessionId, String name)
			throws LoginException, AdminException, HotelException;

	public List<Hotel> getAllHotels(String sessionId) throws LoginException, AdminException, HotelException;

}
