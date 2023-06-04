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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Route;
import com.masai.exception.LoginException;
import com.masai.exception.RouteException;
import com.masai.service.RouteService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
public class RouteController {
	@Autowired
	private RouteService routeService;
	
	@PostMapping("/add/{uuid}")
	public ResponseEntity<Route> addRoute(@Valid @RequestBody Route route, @PathVariable String uuid) throws LoginException, RouteException{
		Route ru = routeService.addRoute(uuid, route);
		return new ResponseEntity<Route>(ru, HttpStatus.OK);
	}
	
	@PutMapping("/update/{uuid}")
	public ResponseEntity<Route> updateRoute(@Valid @RequestBody Route route, @PathVariable String uuid) throws LoginException, RouteException{
		Route ru = routeService.updateRoute(uuid, route);
		return new ResponseEntity<Route>(ru, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{uuid}/{routeid}")
	public ResponseEntity<Route> removeRoute(@PathVariable String uuid, @PathVariable int routeid) throws LoginException, RouteException{
		Route ru = routeService.removeRoute(uuid, routeid);
		return new ResponseEntity<Route>(ru, HttpStatus.OK);
	}
	
	@GetMapping("/search/{routeid}")
	public ResponseEntity<Route> searchRoute(@PathVariable int routeid) throws LoginException, RouteException{
		Route ru = routeService.searchRoute(routeid);
		return new ResponseEntity<Route>(ru, HttpStatus.OK);
	}
	
	@GetMapping("/allroutes")
	public ResponseEntity<List<Route>> allRoute() throws LoginException, RouteException{
		List<Route> list = routeService.viewRouteList();
		return new ResponseEntity<List<Route>>(list, HttpStatus.OK);
	}
	
	
	
	

}
