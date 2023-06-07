package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.masai.entity.Hotel;
import com.masai.entity.Package;

public interface PackageRepository extends JpaRepository<Package, Integer>{
	
	public Package findByPackageName(String packageName); 
	
	
	@Query(value = "select * from hotel h where h.hotel_id IN (select ph.hotel_id from package_hotel ph where ph.package_id=:id group by ph.package_id) ",nativeQuery = true)
	public List<Object[]> getAllHotels(@Param("id") Integer id);
	
//	@Query(value = "SELECT h FROM Hotel h WHERE h.hotel_id IN (SELECT ph.hotelhotel_id FROM Package_Hotel ph WHERE ph.package_id = :id)")
//	public List<Hotel> getAllHotels(@Param("id") Integer id);

	
	
	@Query(value = "select * from hotel where hotel_id IN (select hotel_id from package_hotel where package_id=:id group by package_id) and is_available='1'",nativeQuery = true)
	public List<Object[]> getAvailableHotels(@Param("id") Integer id);
	
}
