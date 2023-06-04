package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	public List<Hotel> findByHotelName(String hotelName);
}
