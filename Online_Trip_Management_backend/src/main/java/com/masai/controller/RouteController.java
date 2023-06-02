package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.masai.service.RouteService;

@RestController
@CrossOrigin("*")
public class RouteController {
	@Autowired
	private RouteService routeService;
	
	
}
