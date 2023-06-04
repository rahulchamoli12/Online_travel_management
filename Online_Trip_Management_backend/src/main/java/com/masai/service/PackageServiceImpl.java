package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Admin;
import com.masai.entity.CurrentUserSession;
import com.masai.entity.Customer;
import com.masai.entity.Package;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.repository.AdminRepository;
import com.masai.repository.PackageRepository;
import com.masai.repository.SessionRepository;

@Service
public class PackageServiceImpl implements PackageService{

	@Autowired
	private PackageRepository packageRepo;
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private SessionRepository sessRepo;
	
	
	@Override
	public Package addPackage(String sessionId,Package pack) throws PackageException, LoginException, AdminException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		Optional<Admin> admin = adminRepo.findById(cus.getUserId());
		if (admin.isEmpty())
			throw new AdminException("user not authorized");
		return packageRepo.save(pack);
	}


	@Override
	public Package deletePackage(String sessionId, Integer packageId)
			throws PackageException, LoginException, AdminException {
		CurrentUserSession cus = sessRepo.findBySessionId(sessionId);
		if (cus == null)
			throw new LoginException("you're not logged in");
		Optional<Admin> admin = adminRepo.findById(cus.getUserId());
		if (admin.isEmpty())
			throw new AdminException("user not authorized");
		Optional<Package> pack = packageRepo.findById(packageId);
		if (pack.isEmpty())
			throw new PackageException("package not found");
		packageRepo.delete(pack.get());
		return pack.get();
	}


	@Override
	public Package searchPackage(Integer packageId) throws PackageException, LoginException, AdminException {
		Optional<Package> pack = packageRepo.findById(packageId);
		if (pack.isEmpty())
			throw new PackageException("package not found");
		return pack.get();
	}


	@Override
	public List<Package> viewAllPackages() throws PackageException, LoginException, AdminException {
		List<Package> packages = packageRepo.findAll();
		if (packages.size()==0)
			throw new PackageException("package not found");
		return packages;
	}

}
