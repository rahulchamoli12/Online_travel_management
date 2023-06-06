package com.masai.service;

import java.util.List;

import com.masai.entity.Hotel;
import com.masai.entity.Package;
import com.masai.exception.AdminException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;

public interface PackageService {
	
	public Package addPackage(String sessionId, Package pack) throws PackageException, LoginException, AdminException;
	
	public Package deletePackage(String sessionId,Integer packageId) throws PackageException, LoginException, AdminException;
	
	public Package searchPackage(Integer packageId) throws PackageException, LoginException, AdminException;
	
	public List<Package> viewAllPackages() throws PackageException, LoginException, AdminException;
	
	public Package searchByPackageTitle(String packageTitle) throws PackageException, AdminException;
	
	public Package assignHotelToPackage(String sessionId,Integer hotelId,Integer packageId) throws PackageException,HotelException,LoginException, AdminException;

	public List<Hotel> getAvailableHotels(Integer packageId) throws PackageException,HotelException;
	
	public List<Hotel> getAllHotels(String sessionId,Integer packageId) throws PackageException,HotelException,AdminException,LoginException;
	
}
