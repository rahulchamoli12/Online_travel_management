package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Admin;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.CurrentUserSession.Role;
import com.masai.entity.Customer;
import com.masai.entity.Hotel;
import com.masai.entity.Package;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.HotelException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.repository.AdminRepository;
import com.masai.repository.HotelRepository;
import com.masai.repository.PackageRepository;
import com.masai.repository.SessionRepository;

@Service
public class PackageServiceImpl implements PackageService {

	// Autowired annotation for the PackageRepository dependency
	@Autowired
	private PackageRepository packageRepo;
	
	// Autowired annotation for the HotelRepository dependency
	@Autowired
	private HotelRepository hotelRepo;

	// Autowired annotation for the SessionRepository dependency
	@Autowired
	private SessionRepository sessRepo;
	
	/**
	 * Adds a new package to the system.
	 *
	 * @param  sessionId The session ID of the logged-in user.
	 * @param  pack The package to be added.
	 * @return The added package.
	 * @throws PackageException If there is an issue with the package.
	 * @throws LoginException   If the user is not logged in.
	 * @throws AdminException   If the user is not authorized as an admin.
	 */
	@Override
	public Package addPackage(String sessionId, Package pack) throws PackageException, LoginException, AdminException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");

		if (cus.getRole() == Role.ADMIN) {
			return packageRepo.save(pack);
		} else {
			throw new AdminException("user not authorized");
		}

	}
	
	
	/**
	 * Deletes a package from the system.
	 *
	 * @param sessionId  The session ID of the logged-in user.
	 * @param packageId  The ID of the package to be deleted.
	 * @return The deleted package.
	 * @throws PackageException If the package is not found.
	 * @throws LoginException   If the user is not logged in.
	 * @throws AdminException   If the user is not authorized as an admin.
	 */
	@Override
	public Package deletePackage(String sessionId, Integer packageId)
			throws PackageException, LoginException, AdminException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		if (cus.getRole() != Role.ADMIN) {
			throw new AdminException("user not authorized");
		}
		Optional<Package> pack = packageRepo.findById(packageId);
		if (pack.isEmpty())
			throw new PackageException("package not found");
		packageRepo.delete(pack.get());
		return pack.get();
	}
	
	/**
	 * Searches for a package by its ID.
	 *
	 * @param packageId The ID of the package to search for.
	 * @return The found package.
	 * @throws PackageException If the package is not found.
	 */
	@Override
	public Package searchPackage(Integer packageId) throws PackageException, LoginException, AdminException {
		Optional<Package> pack = packageRepo.findById(packageId);
		if (pack.isEmpty())
			throw new PackageException("package not found");
		return pack.get();
	}
	
	
	/**
	 * Retrieves all packages in the system.
	 *
	 * @return A list of all packages.
	 * @throws PackageException If no packages are found.
	 */
	@Override
	public List<Package> viewAllPackages() throws PackageException, LoginException, AdminException {
		List<Package> packages = packageRepo.findAll();
		if (packages.size() == 0)
			throw new PackageException("package not found");
		return packages;
	}
	
	
	/**
	 * Searches for a package by its title.
	 *
	 * @param packageTitle The title of the package to search for.
	 * @return The found package.
	 * @throws PackageException If the package is not found.
	 */
	@Override
	public Package searchByPackageTitle(String packageTitle) throws PackageException, AdminException {
		Package packa = packageRepo.findByPackageName(packageTitle);
		if (packa==null)
			throw new PackageException("package not found");
		return packa;
	}
	
	
	/**
	 * Assigns a hotel to a package.
	 *
	 * @param sessionId The session ID of the logged-in user.
	 * @param hotelId   The ID of the hotel to assign.
	 * @param packageId The ID of the package to assign the hotel to.
	 * @return The updated package.
	 * @throws PackageException If the package is not found.
	 * @throws HotelException   If the hotel is not found.
	 * @throws LoginException   If the user is not logged in.
	 * @throws AdminException   If the user is not authorized as an admin.
	 */
	@Override
	public Package assignHotelToPackage(String sessionId, Integer hotelId, Integer packageId)
			throws PackageException, HotelException, LoginException, AdminException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		if (cus.getRole() != Role.ADMIN) {
			throw new AdminException("user not authorized");
		}
		Optional<Hotel> hotel = hotelRepo.findById(hotelId);
		if (hotel.isEmpty())
			throw new HotelException("hotel not found");
		Optional<Package> pack = packageRepo.findById(packageId);
		if (pack.isEmpty())
			throw new PackageException("package not found");
		
		Package packa = pack.get();
		Hotel hot = hotel.get();
		packa.getHotels().add(hot);
		hot.getPackages().add(packa);
		
		packageRepo.save(packa);
	    hotelRepo.save(hot);
		
		return packa;
	}


	@Override
	public List<Hotel> getAvailableHotels(Integer packageId) throws PackageException, HotelException {
		List<Hotel> hotels = packageRepo.getAvailableHotels(packageId);
		if(hotels.size()==0) {
			throw new HotelException("hotel not found");
		}
		return hotels;
	}


	@Override
	public List<Hotel> getAllHotels(String sessionId, Integer packageId)
			throws PackageException, HotelException, AdminException, LoginException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		if (cus.getRole() != Role.ADMIN) {
			throw new AdminException("user not authorized");
		}
		List<Hotel> hotels = packageRepo.getAllHotels(packageId);
		if(hotels.size()==0) {
			throw new HotelException("hotel not found");
		}
		return hotels;
	}

}
