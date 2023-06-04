package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Bus;
import com.masai.entity.Package;
import com.masai.exception.AdminException;
import com.masai.exception.BusException;
import com.masai.exception.LoginException;
import com.masai.exception.PackageException;
import com.masai.service.PackageService;

import jakarta.validation.Valid;

@RestController("/packages")
@CrossOrigin("*")
public class PackageController {
	
	@Autowired
	private PackageService packService;
	
	
	@PostMapping("/create/{sessionId}")
	public ResponseEntity<Package> addPackageController(@Valid @RequestBody Package pack, @PathVariable String sessionId) throws PackageException, LoginException, AdminException {
		Package pp = packService.addPackage(sessionId, pack);
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{sessionId}")
	public ResponseEntity<Package> deletePackageController(@Valid @PathVariable String sessionId,@RequestParam("packageId") Integer packageId) throws PackageException, LoginException, AdminException {
		Package pp = packService.deletePackage(sessionId, packageId);
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Package> searchPackageController(@Valid @RequestParam("packageId") Integer packageId) throws PackageException, LoginException, AdminException {
		Package pp = packService.searchPackage(packageId);
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}
	
	@GetMapping("/viewall")
	public ResponseEntity<List<Package>> viewAllPackageController() throws PackageException, LoginException, AdminException {
		List<Package> pp = packService.viewAllPackages();
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}
	
	@GetMapping("/searchByTitle/{packageTitle}")
	public ResponseEntity<Package> searchByTitlePackageController(@Valid @PathVariable String packageTitle) throws PackageException, LoginException, AdminException {
		Package pp = packService.searchByPackageTitle(packageTitle);
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}
	
	
}
