package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.CurrentUserSession;
import com.masai.entity.Hotel;
import com.masai.entity.CurrentUserSession.Role;
import com.masai.exception.AdminException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.repository.HotelRepository;
import com.masai.repository.SessionRepository;

/**
 * @author Aman_Maurya
 *
 */

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private SessionRepository sessionRepo;

	@Override
	public Hotel registerNewHotel(String sessionId, Hotel hotel) throws LoginException, AdminException, HotelException {

		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User not Logged In!");
		if (cus.getRole() == Role.ADMIN) {
			if (hotel == null)
				throw new HotelException("Hotel Details is Mandatory!");

			return hotelRepo.save(hotel);
		}
		throw new AdminException("User not Authorized!");
	}

	@Override
	public Hotel updateHotelDetails(String sessionId, Hotel hotel)
			throws LoginException, AdminException, HotelException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User not Logged In!");
		if (cus.getRole() == Role.ADMIN) {
			Optional<Hotel> hotelOpt = hotelRepo.findById(hotel.getHotelId());
			if (hotelOpt.isEmpty())
				throw new HotelException("Hotel Details Found in Record!");
			else if (hotel == null)
				throw new HotelException("Hotel Details is Mandatory!");
			else
				return hotelRepo.save(hotel);
		}
		throw new AdminException("User not Authorized!");
	}

	@Override
	public Hotel deleteHotelDetails(String sessionId, Integer hotelId)
			throws LoginException, AdminException, HotelException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User not Logged In!");
		if (cus.getRole() == Role.ADMIN) {
			Optional<Hotel> hotelOpt = hotelRepo.findById(hotelId);
			if (hotelOpt.isEmpty())
				throw new HotelException("Hotel Details Found in Record!");

			hotelRepo.delete(hotelOpt.get());
			return hotelOpt.get();
		}
		throw new AdminException("User not Authorized!");
	}

	@Override
	public Hotel getHotelByHotelId(String sessionId, Integer hotelId)
			throws LoginException, AdminException, HotelException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User not Logged In!");
		if (cus.getRole() == Role.ADMIN) {
			Optional<Hotel> hotelOpt = hotelRepo.findById(hotelId);
			if (hotelOpt.isEmpty())
				throw new HotelException("Hotel Details Found in Record!");

			return hotelOpt.get();
		}
		throw new AdminException("User not Authorized!");
	}

	@Override
	public List<Hotel> getHotelsByHotelName(String sessionId, String name)
			throws LoginException, AdminException, HotelException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User not Logged In!");
		if (cus.getRole() == Role.ADMIN) {
			List<Hotel> hotels = hotelRepo.findByHotelName(name);
			if (hotels.isEmpty())
				throw new HotelException("Hotel Details Found in Record!");

			return hotels;
		}
		throw new AdminException("User not Authorized!");
	}

	@Override
	public List<Hotel> getAllHotels(String sessionId) throws LoginException, AdminException, HotelException {
		CurrentUserSession cus = sessionRepo.findBySessionId(sessionId);

		if (cus == null)
			throw new LoginException("User not Logged In!");
		if (cus.getRole() == Role.ADMIN) {
			List<Hotel> hotels = hotelRepo.findAll();
			if (hotels.isEmpty())
				throw new HotelException("Hotel Details Found in Record!");

			return hotels;
		}
		throw new AdminException("User not Authorized!");
	}

}
