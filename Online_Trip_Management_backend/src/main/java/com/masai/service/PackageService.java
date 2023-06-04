package com.masai.service;

import java.util.List;

import com.masai.entity.Package;
import com.masai.exception.AdminException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;

public interface PackageService {
	
	public Package addPackage(String sessionId, Package pack) throws PackageException, LoginException, AdminException;
	
	public Package deletePackage(String sessionId,Integer packageId) throws PackageException, LoginException, AdminException;
	
	public Package searchPackage(Integer packageId) throws PackageException, LoginException, AdminException;
	
	public List<Package> viewAllPackages() throws PackageException, LoginException, AdminException;
	
	
}
