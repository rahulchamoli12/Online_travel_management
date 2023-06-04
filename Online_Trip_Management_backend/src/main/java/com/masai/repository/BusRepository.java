package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.entity.Bus;
import com.masai.entity.Route;

public interface BusRepository extends JpaRepository<Bus, Integer>{
	
	

}
