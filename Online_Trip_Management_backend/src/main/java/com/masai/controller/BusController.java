package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Bus;
import com.masai.exception.BusException;
import com.masai.exception.LoginException;
import com.masai.service.BusService;

import jakarta.validation.Valid;

@RestController("/online/trip/bus")
@CrossOrigin("*")
public class BusController {
	@Autowired
	private BusService busService;
	
	@PostMapping("/add/{uuid}")
	public ResponseEntity<Bus> addBus(@Valid @RequestBody Bus bus, @PathVariable String uuid) throws LoginException, BusException{
		Bus bu = busService.addBus(uuid, bus);
		return new ResponseEntity<Bus>(bu, HttpStatus.OK);
	}
	
	@PostMapping("/update/{uuid}")
	public ResponseEntity<Bus> updateBus(@Valid @RequestBody Bus bus, @PathVariable String uuid) throws LoginException, BusException{
		Bus bu = busService.updateBus(uuid, bus);
		return new ResponseEntity<Bus>(bu, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{uuid}/{busid}")
	public ResponseEntity<Bus> deleteBus(@PathVariable String uuid, @PathVariable int busid) throws LoginException, BusException{
		Bus bu = busService.deleteBus(uuid, busid);
		return new ResponseEntity<Bus>(bu, HttpStatus.OK);
	}
	
	
}
