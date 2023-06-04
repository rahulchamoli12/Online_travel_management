package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.entity.Bus;
import com.masai.entity.Route;

public interface RouteRepository extends JpaRepository<Route, Integer>{
	
	@Query("SELECT b FROM Route r JOIN r.buses b WHERE r.routeFrom=?1 and r.routeTo=?2 and b.capacity >= ?3 and b.dateOfJourney =?4 order by b.capacity limit 1")
	Bus findByRouteLocation(String routeFrom, String routeTo,Integer capacity,LocalDate dateOfJourney);
	
	
	
}
