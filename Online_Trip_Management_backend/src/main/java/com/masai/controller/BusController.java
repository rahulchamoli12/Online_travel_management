package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.masai.service.BusService;

@RestController
@CrossOrigin("*")
public class BusController {
	@Autowired
	private BusService busService;
}
