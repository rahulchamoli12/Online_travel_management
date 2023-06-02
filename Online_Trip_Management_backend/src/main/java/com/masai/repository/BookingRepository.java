package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
