package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
